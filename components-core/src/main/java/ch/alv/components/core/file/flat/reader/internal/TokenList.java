package ch.alv.components.core.file.flat.reader.internal;


import ch.alv.components.core.file.flat.reader.Transformer;
import ch.alv.components.core.file.flat.reader.TransformerException;
import ch.alv.components.core.file.flat.reader.TransformerParseException;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A custom LinkedList which represents the record as a token of string values.
 * Will take into consideration if the records are character separated or fixed
 * position columns.
 *
 * @since 1.0.0
 */
@SuppressWarnings("serial")
class TokenList extends LinkedList<String> {
    private String src;

    private FlatFileTransformer transformer;

    private Class destClazz;

    /**
     * Tokenize the record given the transformer.
     *
     * @param src the string to tokenize
     * @param transformer the transformer to apply.
     * @param clazz the target class
     */
    public TokenList(String src, FlatFileTransformer transformer, Class clazz) {
        this.src = src;
        this.transformer = transformer;
        destClazz = clazz;

        // apply sane defaults
        if (transformer == null) {
            throw new TransformerException("Invalid file spec.");
        }

        if (src == null) {
            throw new TransformerException(
                    "Source string to parse cannot be null.");
        }

        // parse string into tokens as per file spec
        parse();
    }

    private void parseTokens(String src, char delim) {
        StringBuffer colVal = new StringBuffer();
        boolean quotedValue = false;
        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) == '"' && quotedValue) {
                add(colVal.toString());
                colVal = new StringBuffer();
                quotedValue = false;
                i++;
                continue;
            } else if (src.charAt(i) == '"') {
                quotedValue = true;
                continue;
            }

            if ((!quotedValue && src.charAt(i) != delim) || (quotedValue)
                    || (src.charAt(i) != delim)
                    || (quotedValue && src.charAt(i) == delim)) {
                colVal.append(src.charAt(i));
                continue;
            }

            add(colVal.toString());
            colVal = new StringBuffer();
            quotedValue = false;
        }

        // last column is not added in the loop above so adding it explicitly here
        add(colVal.toString());
    }

    private void parse() {
        if (Transformer.ColumnSeparator.CHARACTER == transformer
                .getColumnSeparatorType()) {
            parseTokens(src, transformer.getColumnSeparator().charAt(0));
        } else if (Transformer.ColumnSeparator.FIXLENGTH == transformer
                .getColumnSeparatorType()) {
            Record rec = transformer.getRecord(destClazz.getName());
            for (int i : rec.indexes()) {
                Column col = rec.getColumnAt(i);
                try {
                    add(src.substring(col.getStartColumn(),
                            col.getEndColumn()));
                } catch (IndexOutOfBoundsException ex) {
                    throw new TransformerParseException(ex);
                }

            }
        } else {
            throw new TransformerException(
                    "Invalid column separator type. Only supports enums in "
                            + Transformer.ColumnSeparator.class.getName());
        }
    }

    public String getRecordIdentifierValue() {
        return get(transformer.getRecordIdentifierColumn());
    }

    public Iterator<String> iterator() {
        return new CustomIterator();
    }

    private Iterator getBaseIterator() {
        return super.iterator();
    }

    public String get(int pos) {
        try {
            return super.get(pos);
        } catch (IndexOutOfBoundsException ex) {
            throw new TransformerParseException(ex);
        }
    }

    private class CustomIterator implements TokenIterator {
        private Iterator it = getBaseIterator();

        public void remove() {
            it.remove();
        }

        public boolean hasNext() {
            return it.hasNext();
        }

        public String next() {
            return (String) it.next();
        }

        public int getInt() {
            return Integer.parseInt(next());
        }

        public long getLong() {
            return Long.parseLong(next());
        }

        public String getString() {
            return next();
        }
    }
}
