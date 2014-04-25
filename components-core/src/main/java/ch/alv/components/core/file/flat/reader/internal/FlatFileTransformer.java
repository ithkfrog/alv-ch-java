package ch.alv.components.core.file.flat.reader.internal;

import ch.alv.components.core.beans.factory.BeanFactory;
import ch.alv.components.core.file.flat.reader.RecordListener;
import ch.alv.components.core.file.flat.reader.Transformer;
import ch.alv.components.core.file.flat.reader.TransformerException;
import ch.alv.components.core.file.flat.reader.TransformerParseException;
import ch.alv.components.core.file.flat.reader.annotations.FlatFileColumn;
import ch.alv.components.core.file.flat.reader.annotations.Transform;
import ch.alv.components.core.utils.StringHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements the {@link Transformer} interface.
 *
 * @since 1.0.0
 */
public final class FlatFileTransformer implements Transformer {
    /**
     * Log messages into this.
     */
    private final static Log logger = LogFactory.getLog(FlatFileTransformer.class);

    /**
     * Default column separator (for delimited columns)
     */
    private final static String DEFAULT_COLUMN_SEPARATOR_CHARACTER = ";";

    /**
     * Default column separator is CHARACTER (and not fixed length columns).
     */
    private ColumnSeparator columnSeparatorType = ColumnSeparator.CHARACTER;

    /**
     * Default column separator (for delimited columns)
     */
    private String columnSeparator = DEFAULT_COLUMN_SEPARATOR_CHARACTER;

    /**
     * Column number that is the identifier for the record. An identifier is
     * used to uniquely map a record to a certain record type. This is needed
     * when we {@link RecordListener} is used.
     */
    private int idColumnIndex = 0;

    /**
     * An external reference to a factory that is responsible for creating the
     * beans into which the data is read into. By default
     * {@link ch.alv.components.core.file.flat.reader.DefaultBeanFactory} is used which simply creates a new instance
     * using the default constructor.
     */
    private BeanFactory beanCreator;

    /**
     * Bean class name to {@link Record} instance.
     */
    private Map<String, Record> recordMap = new HashMap<>();

    private boolean skipFirstLine;

    private Class<Transform> clazz;

    /**
     * Initialize the transformer.
     *
     * @param clazz the transformer class.
     */
    public FlatFileTransformer(Class<Transform> clazz) {
        this.clazz = clazz;
        loadClasses();
    }

    /**
     * Loads the FFT annotated classes and loads them into an internal cache for
     * fast reference later.
     */
    private void loadClasses() {

        if (logger.isDebugEnabled()) {
            logger.debug("Reading record metadata from annotations in class "
                    + clazz.getClass().getName());
        }
        Transform classAnnotation = clazz.getAnnotation(Transform.class);
        setBeanCreator(classAnnotation.beanCreator());
        setColumnSeparator(classAnnotation.columnSeparator());
        setColumnSeparatorType(classAnnotation.columnSeparatorType());
        setSkipFirstLine(classAnnotation.skipFirstLine());

        // parse column mappings for this record
        parseRecordMappingDetails();

    }

    /***/
    public void parseFlatFile(InputStream stream, RecordListener listener) {
        if (stream == null) {
            throw new TransformerException(
                    "Cannot read file. Invalid InputStream.");
        }

        parse(new BufferedReader(new InputStreamReader(stream)), listener);
    }

    /***/
    public void parseFlatFile(File file, RecordListener listener) {
        if (file == null || !file.exists() || !file.canRead()
                || file.isDirectory()) {
            throw new TransformerException("Cannot read file " + file);
        }
        try {
            parse(new BufferedReader(new FileReader(file)), listener);
        } catch (FileNotFoundException e) {
            throw new TransformerException(e);
        }
    }

    /***/
    public Object loadRecord(String line) {
        Object dest = beanCreator.createBean(clazz);

        Map<String, Object> map = new HashMap<>();
        TokenList tokens = new TokenList(line, this, clazz);
        Record rec = recordMap.get(clazz.getName());
        for (int i : rec.indexes()) {
            Column col = rec.getColumnAt(i);
            if (col.isSkip()) {
                continue;
            }

            if (col.getType().equals(Date.class.getName())) {
                SimpleDateFormat formatter = new SimpleDateFormat(
                        col.getDateFormat());
                try {
                    map.put(col.getName(), formatter.parse(tokens
                            .get(col.getIndex())));
                } catch (ParseException e) {
                    throw new TransformerParseException(e);
                }
            } else {
                String coldata = tokens.get(col.getIndex());
                map.put(col.getName(), coldata);
            }
        }

        // use BeanUtils to load data into bean
        try {
            BeanUtils.populate(dest, map);
        } catch (Exception e) {
            throw new TransformerParseException(e);
        }
        return dest;
    }

    /**
     * Reads the specified class and loads into (if not already loaded) a map
     * containing the bean class name to a {@link Record} instance. The Record
     * instance will contain metadata about the record format.
     */
    private void parseRecordMappingDetails() {
        if (!recordMap.containsKey(clazz.getName())) {
            synchronized (recordMap) {
                Record rec = new Record(clazz.getName());
                recordMap.put(rec.getName(), rec);

                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(FlatFileColumn.class)) {
                        String colname = field.getName();
                        if (field.getName().startsWith("get")
                                || field.getName().startsWith("set")) {
                            colname = colname.substring(3);
                        }
                        colname = Character.toLowerCase(colname.charAt(0))
                                + colname.substring(1);
                        FlatFileColumn annot = field.getAnnotation(FlatFileColumn.class);
                        // String name, String type, boolean required, int
                        // index, String format, boolean skip
                        Column col = new Column(colname, field.getType()
                                .getName(), annot.required(), annot.position(),
                                annot.format(), annot.skip());
                        col.setStartColumn(annot.start());
                        col.setEndColumn(annot.end());
                        rec.addColumn(col);
                    }
                }
            }
        }
    }

    /**
     * Parse the file contents and register the listener to which we send
     * individual records read from the file. File contents will be read until
     * either the complete file is read or if the {@link RecordListener} returns
     * a <code>false</code> requesting the framework to stop reading the rest of
     * the file.
     *
     * @param reader   file contents
     * @param listener listener waiting for records
     */
    private void parse(BufferedReader reader, RecordListener listener) {
        if (listener == null) {
            throw new TransformerException("Expecting non-null instance of "
                    + RecordListener.class.getName());
        }

        try {
            String line;
            boolean continueReading = true;
            long lineCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;

                if (skipFirstLine && lineCount == 1) {
                    continue;
                }

                // check if we need to stop reading
                if (!continueReading) {
                    logger.info("Aborted reading of file at line# " + lineCount);
                    break;
                }

                // ok read on
                Object o = loadRecord(line);
                if (o != null) {
                    continueReading = listener.foundRecord(o);
                } else {
                    continueReading = listener.unresolvableRecord(line);
                }

            }
        } catch (IOException e) {
            throw new TransformerException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new TransformerException(e);
                }
            }
        }
    }

    // ----------------------------------------------------------------------
    // Setters/Getters.
    // ----------------------------------------------------------------------
    public Record getRecord(String string) {
        return recordMap.get(string);
    }

    public ColumnSeparator getColumnSeparatorType() {
        return columnSeparatorType;
    }

    public void setColumnSeparatorType(ColumnSeparator columnSeparatorType) {
        this.columnSeparatorType = columnSeparatorType;
    }

    public void setBeanCreator(BeanFactory beanCreator) {
        this.beanCreator = beanCreator;
    }

    public int getIdColumnIndex() {
        return idColumnIndex;
    }

    public BeanFactory getBeanCreator() {
        return beanCreator;
    }

    public String getColumnSeparator() {
        return columnSeparator;
    }

    public void setColumnSeparator(String separator) {
        this.columnSeparator = separator;

        if (columnSeparator == null || columnSeparator.trim().length() == 0) {
            columnSeparator = DEFAULT_COLUMN_SEPARATOR_CHARACTER;
        }

    }

    public boolean isSkipFirstLine() {
        return skipFirstLine;
    }

    public void setSkipFirstLine(boolean skipFirstLine) {
        this.skipFirstLine = skipFirstLine;
    }

    public int getRecordIdentifierColumn() {
        return idColumnIndex;
    }

    public void setRecordIdentifierColumn(int idcol) {
        idColumnIndex = idcol;
        if (idColumnIndex < 0) {
            idColumnIndex = 0;
        }
        idColumnIndex--;
    }

    /**
     * Sets the factory that constructs the beans into which record data is to
     * be loaded.
     *
     * @param beanFactory the name of the beanFactory.
     */
    public void setBeanCreator(String beanFactory) {
        if (StringHelper.isEmpty(beanFactory)) {
            throw new TransformerException(
                    "An implementation of the interface "
                            + BeanFactory.class.getName()
                            + " must be provided.");
        }

        try {
            Class clazz = Class.forName(beanFactory);
            if (!BeanFactory.class.isAssignableFrom(clazz)) {
                throw new TransformerException(beanFactory
                        + " does not implement " + BeanFactory.class.getName());
            }
            beanCreator = (BeanFactory) clazz.newInstance();
        } catch (Exception e) {
            throw new TransformerException(e);
        }
    }
}
