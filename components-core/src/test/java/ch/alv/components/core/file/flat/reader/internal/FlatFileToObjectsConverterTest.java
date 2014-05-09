package ch.alv.components.core.file.flat.reader.internal;

import ch.alv.components.core.beans.factory.StringBeanFactory;
import ch.alv.components.core.file.flat.reader.FlatFileConverterException;
import ch.alv.components.core.file.flat.reader.FlatFileObjectHandle;
import ch.alv.components.core.mock.*;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Test for the {@link FlatFileToObjectsConverterTest}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class FlatFileToObjectsConverterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    static int recCount = 0;

    @Before
    public void init() {
        recCount = 0;
    }

    @Test
    public void testConstructor() throws InstantiationException, IllegalAccessException {
        new FlatFileToObjectsConverter<>(MockBeanB.class);
    }

    @Test
    public void testMissingAnnotation() throws InstantiationException, IllegalAccessException {
        exception.expect(FlatFileConverterException.class);
        exception.expectMessage("Could not find @FlatFileConversion annotation on class 'ch.alv.components.core.mock.MockBeanA'.");
        new FlatFileToObjectsConverter<>(MockBeanA.class);
    }

    @Test
    public void testSimpleStringConversion() throws InstantiationException, IllegalAccessException {
        String row = "1;Jon;Doe;true";
        MockSemicolonDelimitedBean bean = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedBean.class).convert(row);
        assertEquals((Integer) 1, bean.getId());
        assertEquals("Jon", bean.getFirstName());
        assertEquals("Doe", bean.getLastName());
        assertTrue(bean.getActive());
    }

    @Test
    public void testSimpleStringEmptyValue() throws InstantiationException, IllegalAccessException {
        assertNull(new FlatFileToObjectsConverter<>(MockSemicolonDelimitedBean.class).convert(""));
    }

    @Test
    public void testSimpleStringNullValue() throws InstantiationException, IllegalAccessException {
        assertNull(new FlatFileToObjectsConverter<>(MockSemicolonDelimitedBean.class).convert(null));
    }

    @Test
    public void testDelimitedColumnStreamRead() throws InstantiationException, IllegalAccessException {
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedBean.class);
        spec.convert(getClass().getResourceAsStream("semicolon-delimited-test-data.csv"), new FlatFileTestHandle());
    }

    @Test
    public void testDelimitedColumnMappingFail() throws InstantiationException, IllegalAccessException {
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedBean.class);
        spec.convert(getClass().getResourceAsStream("semicolon-delimited-test-data-failing.csv"), new FlatFileTestHandle());
    }

    @Test
    public void testFixLengthColumnStreamRead() throws InstantiationException, IllegalAccessException {
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockFixLengthBean.class);
        spec.convert(getClass().getResourceAsStream("fix-length-test-data.csv"), new FlatFileTestHandle());
    }

    @Test
    public void testFixLengthIndexFail() throws InstantiationException, IllegalAccessException {
        exception.expect(FlatFileConverterException.class);
        exception.expectMessage("Could not convert '1    hans           \"meier;beck\"        false         ' to a ch.alv.components.core.mock.MockFailingFixLengthBean object.");
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockFailingFixLengthBean.class);
        spec.convert(getClass().getResourceAsStream("fix-length-test-data.csv"), new FlatFileTestHandle());
    }

    @Test
    public void testNullStreamException() throws InstantiationException, IllegalAccessException {
        exception.expect(FlatFileConverterException.class);
        exception.expectMessage("Cannot read file. Invalid InputStream.");
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockFixLengthBean.class);
        spec.convert(null, new FlatFileTestHandle());
    }

    @Test
    public void testColSkipping() throws InstantiationException, IllegalAccessException {
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedColSkippingBean.class);
        spec.convert(getClass().getResourceAsStream("semicolon-delimited-test-data.csv"), new FlatFileObjectHandle<MockSemicolonDelimitedColSkippingBean>() {
            @Override
            public boolean handle(MockSemicolonDelimitedColSkippingBean object) {
                if (object.getId() == 1) {
                    assertNull(object.getFirstName());
                    assertEquals("meier;beck", object.getLastName());
                    assertFalse(object.getActive());
                } else if (object.getId() == 2) {
                    assertNull(object.getFirstName());
                    assertEquals("blaser", object.getLastName());
                    assertTrue(object.getActive());
                }
                return true;
            }
        });
    }

    @Test
    public void testDateFormatNoHeaders() throws InstantiationException, IllegalAccessException {
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedDateTestLineSkippingBean.class);
        spec.convert(getClass().getResourceAsStream("semicolon-delimited-date-test-data.csv"), new FlatFileObjectHandle<MockSemicolonDelimitedDateTestLineSkippingBean>() {
            @Override
            public boolean handle(MockSemicolonDelimitedDateTestLineSkippingBean object) {
                assertEquals(1, object.getId());

                try {
                    Date firstDate = DateUtils.parseDate("2014-01-01", "yyyy-MM-dd");
                    Date secondDate = DateUtils.parseDate("2014-01-01 13:11:55", "yyyy-MM-dd HH:mm:ss");
                    assertEquals(1, object.getId());
                    assertEquals(firstDate, object.getSimpleDate());
                    assertEquals(secondDate, object.getComplexDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    @Test
    public void testDateFormat() throws InstantiationException, IllegalAccessException {
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedDateTestBean.class);
        spec.convert(getClass().getResourceAsStream("semicolon-delimited-date-test-data.csv"), new FlatFileObjectHandle<MockSemicolonDelimitedDateTestBean>() {
            @Override
            public boolean handle(MockSemicolonDelimitedDateTestBean object) {
                assertEquals(1, object.getId());

                try {
                    Date firstDate = DateUtils.parseDate("2014-01-01", "yyyy-MM-dd");
                    Date secondDate = DateUtils.parseDate("2014-01-01 13:11:55", "yyyy-MM-dd HH:mm:ss");
                    assertEquals(1, object.getId());
                    assertEquals(firstDate, object.getSimpleDate());
                    assertEquals(secondDate, object.getComplexDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }


    @Test
    public void testIllegalDateFormat() throws InstantiationException, IllegalAccessException {
        exception.expect(FlatFileConverterException.class);
        exception.expectMessage("Could not convert '1;noDate;noDate' to a ch.alv.components.core.mock.MockSemicolonDelimitedDateTestBean object.");
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedDateTestBean.class);
        spec.convert(getClass().getResourceAsStream("semicolon-delimited-date-test-data-failing.csv"), new FlatFileObjectHandle<MockSemicolonDelimitedDateTestBean>() {
            @Override
            public boolean handle(MockSemicolonDelimitedDateTestBean object) {
                return true;
            }
        });
    }

    @Test
    public void testNullHandle() throws InstantiationException, IllegalAccessException {
        exception.expect(FlatFileConverterException.class);
        exception.expectMessage("Cannot read file. Invalid InputStream.");
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedDateTestBean.class);
        spec.convert(getClass().getResourceAsStream("semicolon-delimited-date-fail-test.csv"), null);
    }

    @Test
    public void testGettersAndSetters() throws InstantiationException, IllegalAccessException {
        FlatFileToObjectsConverter spec = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedBean.class);
        assertEquals(0, spec.getIdColumnIndex());
        assertNotNull(spec.getBeanFactory());
        spec.setColumnSeparator(null);
        assertEquals(";", spec.getColumnSeparator());
        spec.setColumnSeparator("-");
        assertEquals("-", spec.getColumnSeparator());
        assertTrue(spec.isSkipFirstLine());
        assertEquals(0, spec.getRecordIdentifierColumn());
        spec.setRecordIdentifierColumn(4);
        assertEquals(3, spec.getRecordIdentifierColumn());
        spec.setRecordIdentifierColumn(-1);
        assertEquals(0, spec.getRecordIdentifierColumn());
    }

    @Test
    public void testStop() throws InstantiationException, IllegalAccessException {
        StringBeanFactory spec = new FlatFileToObjectsConverter<>(MockSemicolonDelimitedBean.class);
        final int[] counter = {0};
        spec.convert(getClass().getResourceAsStream("semicolon-delimited-test-data.csv"), new FlatFileObjectHandle<MockSemicolonDelimitedBean>() {
            @Override
            public boolean handle(MockSemicolonDelimitedBean object) {
                counter[0]++;
                return false;
            }
        });
        assertEquals(1, counter[0]);
    }

    private class FlatFileTestHandle implements FlatFileObjectHandle<MockFlatFileBean> {
        @Override
        public boolean handle(MockFlatFileBean object) {
            recCount++;
            // there are 2 records in the file and the assertions check for data
            // in each
            if (recCount == 1) {
                assertFalse(object.getActive());
                assertEquals("hans", object.getFirstName());
                assertEquals("meier;beck", object.getLastName());
            } else {
                assertTrue(object.getActive());
                assertEquals("kurt", object.getFirstName());
                assertEquals("blaser", object.getLastName());
            }
            return true;
        }
    }

}
