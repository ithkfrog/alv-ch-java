package ch.alv.components.core.file.flat.reader.internal;


import ch.alv.components.core.file.flat.reader.ConverterException;
import ch.alv.components.core.file.flat.reader.ConverterParseException;
import ch.alv.components.core.file.flat.reader.FlatFileColumnSeparatorType;
import ch.alv.components.core.utils.StringHelper;

import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * A custom LinkedList which represents the record as a token of string values.
 * Will take into consideration if the records are character separated or fixed
 * position columns.
 *
 * @since 1.0.0
 */
class FlatFileRecordTokenListAdapter {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = -6635396785376384674L;

    private final LinkedList<String> list = new LinkedList<>();

    private String src;

    private FlatFileToObjectsConverter transformer;

    private Class targetClass;

    /**
     * Tokenize the record with the given converter.
     *
     * @param src the string to tokenize
     * @param converter the converter to apply.
     * @param targetClass the target class
     */
    public FlatFileRecordTokenListAdapter(String src, FlatFileToObjectsConverter converter, Class targetClass) {
        this.src = src;
        this.transformer = converter;
        this.targetClass = targetClass;

        if (StringHelper.isEmpty(src)) {
            throw new ConverterException("Source string to parse cannot be null.");
        }
        if (converter == null) {
            throw new ConverterException("Converter to apply must not be null.");
        }
        if (targetClass == null) {
            throw new ConverterException("The targetClass must not be null.");
        }
        parse();
    }

    private void parseTokens(String src, char delimiter) {
        Pattern p = Pattern.compile(delimiter + "(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
        String[] fields = p.split(src);
        for (String field : fields) {
            list.add(prepareValue(field));
        }
    }

    private void parse() {
        if (FlatFileColumnSeparatorType.CHARACTER == transformer.getSeparatorType()) {
            parseTokens(src, transformer.getColumnSeparator().charAt(0));
        } else {
            parseRecordIndices();
        }
    }

    private void parseRecordIndices() {
        FlatFileRecord rec = transformer.getRecord(targetClass.getName());
        for (int i : rec.indexes()) {
            try {
                FlatFileColumn col = rec.getColumnAt(i);
                list.add(prepareValue(src.substring(col.getStartColumn(), col.getEndColumn())));
            } catch (IndexOutOfBoundsException ex) {
                throw new ConverterParseException(ex);
            }
        }
    }

    private String prepareValue(String substring) {
        return substring.trim().replace("\"", "");
    }

    public String getRecordIdentifierValue() {
        return get(transformer.getRecordIdentifierColumn());
    }

    public String get(int pos) {
        try {
            return list.get(pos);
        } catch (IndexOutOfBoundsException ex) {
            throw new ConverterParseException(ex);
        }
    }


}
