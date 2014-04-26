package ch.alv.components.core.file.flat.reader.internal;

import ch.alv.components.core.beans.factory.BeanFactory;
import ch.alv.components.core.file.flat.reader.*;
import ch.alv.components.core.file.flat.reader.annotations.FlatFileConversion;
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
 * Implements the {@link ch.alv.components.core.file.flat.reader.StringToObjectConverter} interface.
 *
 * @since 1.0.0
 */
public final class FlatFileToObjectsConverter<T> implements StringToObjectConverter<T> {
    /**
     * Log messages into this.
     */
    private final static Log LOG = LogFactory.getLog(FlatFileToObjectsConverter.class);

    /**
     * Default column separator (for delimited columns)
     */
    private final static String DEFAULT_COLUMN_SEPARATOR_CHARACTER = ";";

    /**
     * Default column separator is CHARACTER (and not fixed length columns).
     */
    private FlatFileColumnSeparatorType separatorType = FlatFileColumnSeparatorType.CHARACTER;

    /**
     * Default column separator (for delimited columns)
     */
    private String columnSeparator = DEFAULT_COLUMN_SEPARATOR_CHARACTER;

    /**
     * Column number that is the identifier for the record. An identifier is
     * used to uniquely map a record to a certain record type. This is needed
     * when we {@link ch.alv.components.core.file.flat.reader.ObjectHandle} is used.
     */
    private int idColumnIndex = 0;

    /**
     * An external reference to a factory that is responsible for creating the
     * beans into which the data is read into. By default
     * {@link FlatFileBeanFactory} is used which simply creates a new instance
     * using the default constructor.
     */
    private BeanFactory beanFactory;

    /**
     * Bean class name to {@link FlatFileRecord} instance.
     */
    private final Map<String, FlatFileRecord> recordMap = new HashMap<>();

    private boolean skipFirstLine;

    private Class<T> targetClass;

    /**
     * Initialize the converter.
     *
     * @param targetClass the class of which the created beans should be.
     */
    public FlatFileToObjectsConverter(Class<T> targetClass) throws InstantiationException, IllegalAccessException {
        this.targetClass = targetClass;
        parseGeneralMetaData();
        parseColumnsMetaData();
    }

    private void parseGeneralMetaData() throws InstantiationException, IllegalAccessException {
        FlatFileConversion classAnnotation = targetClass.getAnnotation(FlatFileConversion.class);
        if (classAnnotation == null) {
            throw new ConverterException("Could not find @FlatFileConversion annotation on class '" + targetClass.getName() + "'.");
        }
        setBeanFactory(classAnnotation.beanFactory().newInstance());
        setColumnSeparator(classAnnotation.columnSeparator());
        setSeparatorType(classAnnotation.separatorType());
        setSkipFirstLine(classAnnotation.skipFirstLine());
    }

    /**
     * Reads the specified class and loads into (if not already loaded) a map
     * containing the bean class name to a {@link FlatFileRecord} instance. The FlatFileRecordDescriptor
     * instance will contain metadata about the record format.
     */
    private void parseColumnsMetaData() {
        synchronized (recordMap) {
            FlatFileRecord rec = new FlatFileRecord(targetClass.getName());
            recordMap.put(rec.getName(), rec);

            for (Field field : targetClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(ch.alv.components.core.file.flat.reader.annotations.FlatFileColumn.class)) {
                    String columnName = field.getName();
                    columnName = Character.toLowerCase(columnName.charAt(0))
                            + columnName.substring(1);
                    ch.alv.components.core.file.flat.reader.annotations.FlatFileColumn annot = field.getAnnotation(ch.alv.components.core.file.flat.reader.annotations.FlatFileColumn.class);
                    // String name, String type, boolean required, int
                    // index, String format, boolean skip
                    FlatFileColumn col = new FlatFileColumn(columnName, field.getType()
                            .getName(), annot.required(), annot.position(),
                            annot.format(), annot.skip());
                    col.setStartColumn(annot.start());
                    col.setEndColumn(annot.end());
                    rec.addColumn(col);
                }
            }
        }
    }

    /***/
    public void convert(InputStream stream, ObjectHandle listener) {
        if (stream == null) {
            throw new ConverterException("Cannot read file. Invalid InputStream.");
        }
        parse(new BufferedReader(new InputStreamReader(stream)), listener);
    }

    /***/
    public void convert(File file, ObjectHandle listener) {
        if (file == null || !file.exists() || !file.canRead() || file.isDirectory()) {
            throw new ConverterException("Cannot read file " + file);
        }
        try {
            parse(new BufferedReader(new FileReader(file)), listener);
        } catch (FileNotFoundException e) {
            throw new ConverterException(e);
        }
    }

    /***/
    public T convert(String input) {
        T target = beanFactory.createBean(targetClass);
        Map<String, Object> map = new HashMap<>();
        FlatFileRecordTokenListAdapter tokens = new FlatFileRecordTokenListAdapter(input, this, targetClass);
        FlatFileRecord rec = recordMap.get(targetClass.getName());
        for (int i : rec.indexes()) {
            FlatFileColumn col = rec.getColumnAt(i);
            if (col.isSkip()) {
                continue;
            }
            if (col.getType().equals(Date.class.getName())) {
                SimpleDateFormat formatter = new SimpleDateFormat(
                        col.getDateFormat());
                try {
                    map.put(col.getName(), formatter.parse(tokens.get(col.getIndex())));
                } catch (ParseException e) {
                    throw new ConverterParseException(e);
                }
            } else {
                String coldata = tokens.get(col.getIndex());
                map.put(col.getName(), coldata);
            }
        }

        // use BeanUtils to load data into bean
        try {
            BeanUtils.populate(target, map);
        } catch (Exception e) {
            throw new ConverterParseException(e);
        }
        return target;
    }

    /**
     * Parse the reader content and register the handle to which we send
     * individual records read from the file. Reader content will be read until
     * either the complete file is read or if the {@link ch.alv.components.core.file.flat.reader.ObjectHandle} returns
     * a <code>false</code> requesting the framework to stop reading the rest of
     * the reader.
     *
     * @param reader   file contents
     * @param handle handle waiting for records
     */
    @SuppressWarnings("unchecked")
    private void parse(BufferedReader reader, ObjectHandle handle) {
        if (handle == null) {
            throw new ConverterException("Expecting non-null instance of " + ObjectHandle.class.getName());
        }
        try {
            String line;
            boolean continueReading = true;
            long lineCount = 1;
            while ((line = reader.readLine()) != null) {
                if (skipFirstLine && lineCount == 1) {
                    lineCount++;
                    continue;
                }

                // check if we need to stop reading
                if (!continueReading) {
                    LOG.info("Aborted reading of file at line# " + lineCount);
                    break;
                }
                // ok read on
                Object o = convert(line);
                if (o != null) {
                    continueReading = handle.handle(o);
                } else {
                    throw new ConverterException("Could not convert '" + line + "' to a " + targetClass.getName() + " object.");
                }
                lineCount++;
            }
        } catch (IOException e) {
            throw new ConverterException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                LOG.error("Error while closing the fileReader.", e);
            }
        }
    }

    // ----------------------------------------------------------------------
    // Setters/Getters.
    // ----------------------------------------------------------------------
    public FlatFileRecord getRecord(String string) {
        return recordMap.get(string);
    }

    public FlatFileColumnSeparatorType getSeparatorType() {
        return separatorType;
    }

    public void setSeparatorType(FlatFileColumnSeparatorType separatorType) {
        this.separatorType = separatorType;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public int getIdColumnIndex() {
        return idColumnIndex;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getColumnSeparator() {
        return columnSeparator;
    }

    public void setColumnSeparator(String separator) {
        if (StringHelper.isEmpty(separator)) {
            columnSeparator = DEFAULT_COLUMN_SEPARATOR_CHARACTER;
        } else {
            this.columnSeparator = separator;
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

}
