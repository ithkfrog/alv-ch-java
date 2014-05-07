package ch.alv.components.core.file.flat.reader.internal;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the {@link FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
public class FlatFileColTest {


    public static final String COLUMN_NAME = "columnName";
    public static final String COLUMN_TYPE = "columnType";
    public static final Integer COLUMN_INDEX = 0;
    public static final String COLUMN_DATE_FORMAT = "columnDateFormat";
    public static final String COLUMN_NAME_NEW = "newColumnName";
    public static final int COLUMN_START = 0;
    public static final int COLUMN_END = 1;
    public static final String COLUMN_TYPE_NEW = "newColumnType";

    @Test
    public void testConstructor() {
        FlatFileCol flatFileCol = new FlatFileCol(COLUMN_NAME, COLUMN_TYPE, true, COLUMN_INDEX, COLUMN_DATE_FORMAT, true);
        assertEquals(COLUMN_NAME, flatFileCol.getName());
        assertEquals(COLUMN_TYPE, flatFileCol.getType());
        assertTrue(flatFileCol.isRequired());
        assertEquals(COLUMN_INDEX, flatFileCol.getIndex());
        assertEquals(COLUMN_DATE_FORMAT, flatFileCol.getDateFormat());
        assertTrue(flatFileCol.isSkip());
    }

    @Test
    public void testSettersAndGetters() {
        FlatFileCol flatFileCol = new FlatFileCol(COLUMN_NAME, COLUMN_TYPE, true, COLUMN_INDEX, COLUMN_DATE_FORMAT, true);

        flatFileCol.setName(COLUMN_NAME_NEW);
        flatFileCol.setStartColumn(COLUMN_START);
        flatFileCol.setEndColumn(COLUMN_END);
        flatFileCol.setRequired(false);
        flatFileCol.setType(COLUMN_TYPE_NEW);

        assertEquals(COLUMN_NAME_NEW, flatFileCol.getName());
        assertEquals(COLUMN_START, flatFileCol.getStartColumn());
        assertEquals(COLUMN_END, flatFileCol.getEndColumn());
        assertFalse(flatFileCol.isRequired());
        assertEquals(COLUMN_TYPE_NEW, flatFileCol.getType());
        assertTrue(flatFileCol.isSkip());
    }

}
