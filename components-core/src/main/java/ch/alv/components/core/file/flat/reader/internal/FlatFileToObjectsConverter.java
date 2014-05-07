package ch.alv.components.core.file.flat.reader.internal;

import ch.alv.components.core.beans.factory.BeanFactory;
import ch.alv.components.core.beans.factory.StringBeanFactory;
import ch.alv.components.core.file.flat.reader.FlatFileColumnSeparatorType;
import ch.alv.components.core.file.flat.reader.FlatFileConverterException;
import ch.alv.components.core.file.flat.reader.FlatFileObjectHandle;
import ch.alv.components.core.file.flat.reader.annotations.FlatFileConversion;
import ch.alv.components.core.utils.IoHelper;
import ch.alv.components.core.utils.StringHelper;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements the {@link ch.alv.components.core.beans.factory.StringBeanFactory} interface.
 *
 * @since 1.0.0
 */
public final class FlatFileToObjectsConverter<T> implements StringBeanFactory<T> {
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
     * when we {@link ch.alv.components.core.file.flat.reader.FlatFileObjectHandle} is used.
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
            throw new FlatFileConverterException("Could not find @FlatFileConversion annotation on class '" + targetClass.getName() + "'.");
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
                    FlatFileCol col = new FlatFileCol(columnName, field.getType()
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
    public void convert(InputStream stream, FlatFileObjectHandle handle) {
        if (stream == null) {
            throw new FlatFileConverterException("Cannot read file. Invalid InputStream.");
        }
        parse(new BufferedReader(new InputStreamReader(stream)), handle);
    }

    /***/
    public T convert(String input) {
        T target = beanFactory.createBean(targetClass);
        try {
            BeanUtilsBean.getInstance().populate(target, prepareDataMap(input));
        } catch (Exception e) {
            LOG.error("Error while mapping values to bean: '" + input + "', null will be returned.", e);
            target = null;
        }
        return target;
    }

    private Map<String, Object> prepareDataMap(String input) {
        Map<String, Object> map = new HashMap<>();
        FlatFileRecordTokenListAdapter tokens = new FlatFileRecordTokenListAdapter(input, this, targetClass);
        FlatFileRecord rec = recordMap.get(targetClass.getName());
        for (int i : rec.indexes()) {
            putColumnDataOnValueMap(map, tokens, rec.getColumnAt(i));
        }
        return map;
    }

    private void putColumnDataOnValueMap(Map<String, Object> map, FlatFileRecordTokenListAdapter tokens, FlatFileCol col) {
        if (col.isSkip()) {
            return;
        }
        if (col.getType().equals(Date.class.getName())) {
            SimpleDateFormat formatter = new SimpleDateFormat(
                    col.getDateFormat());
            try {
                map.put(col.getName(), formatter.parse(tokens.get(col.getIndex())));
            } catch (ParseException e) {
                throw new FlatFileConverterException("Error while parsing String value.", e);
            }
        } else {
            String coldata = tokens.get(col.getIndex());
            map.put(col.getName(), coldata);
        }
    }

    /**
     * Parse the reader content and register the handle to which we send
     * individual records read from the file. Reader content will be read until
     * either the complete file is read or if the {@link ch.alv.components.core.file.flat.reader.FlatFileObjectHandle} returns
     * a <code>false</code> requesting the framework to stop reading the rest of
     * the reader.
     *
     * @param reader file contents
     * @param handle handle waiting for records
     */
    @SuppressWarnings("unchecked")
    private void parse(BufferedReader reader, FlatFileObjectHandle handle) {
        try {
            parseLines(reader, handle);
        } catch (IOException e) {
            throw new FlatFileConverterException(e);
        } finally {
            IoHelper.closeReaderQuietly(reader);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseLines(BufferedReader reader, FlatFileObjectHandle handle) throws IOException {
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
                throw new FlatFileConverterException("Could not convert '" + line + "' to a " + targetClass.getName() + " object.");
            }
            lineCount++;
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
            idColumnIndex = 1;
        }
        idColumnIndex--;
    }

}
