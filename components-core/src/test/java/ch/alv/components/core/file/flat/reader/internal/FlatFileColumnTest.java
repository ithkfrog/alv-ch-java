package ch.alv.components.core.file.flat.reader.internal;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the {@link FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
public class FlatFileColumnTest {


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
        FlatFileColumn flatFileColumn = new FlatFileColumn(COLUMN_NAME, COLUMN_TYPE, true, COLUMN_INDEX, COLUMN_DATE_FORMAT, true);
        assertEquals(COLUMN_NAME, flatFileColumn.getName());
        assertEquals(COLUMN_TYPE, flatFileColumn.getType());
        assertTrue(flatFileColumn.isRequired());
        assertEquals(COLUMN_INDEX, flatFileColumn.getIndex());
        assertEquals(COLUMN_DATE_FORMAT, flatFileColumn.getDateFormat());
        assertTrue(flatFileColumn.isSkip());
    }

    @Test
    public void testSettersAndGetters() {
        FlatFileColumn flatFileColumn = new FlatFileColumn(COLUMN_NAME, COLUMN_TYPE, true, COLUMN_INDEX, COLUMN_DATE_FORMAT, true);

        flatFileColumn.setName(COLUMN_NAME_NEW);
        flatFileColumn.setStartColumn(COLUMN_START);
        flatFileColumn.setEndColumn(COLUMN_END);
        flatFileColumn.setRequired(false);
        flatFileColumn.setType(COLUMN_TYPE_NEW);

        assertEquals(COLUMN_NAME_NEW, flatFileColumn.getName());
        assertEquals(COLUMN_START, flatFileColumn.getStartColumn());
        assertEquals(COLUMN_END, flatFileColumn.getEndColumn());
        assertFalse(flatFileColumn.isRequired());
        assertEquals(COLUMN_TYPE_NEW, flatFileColumn.getType());
        assertTrue(flatFileColumn.isSkip());
    }

}
