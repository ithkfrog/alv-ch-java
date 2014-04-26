package ch.alv.components.core.file.flat.reader.internal;

import ch.alv.components.core.file.flat.reader.ConverterException;
import ch.alv.components.core.file.flat.reader.ObjectHandle;
import ch.alv.components.core.file.flat.reader.StringToObjectConverter;
import ch.alv.components.core.test.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for the {@link FlatFileBeanFactory}.
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
        new FlatFileToObjectsConverter<>(BeanB.class);
    }

    @Test
    public void testMissingAnnotation() throws InstantiationException, IllegalAccessException {
        exception.expect(ConverterException.class);
        exception.expectMessage("Could not find @FlatFileConversion annotation on class 'ch.alv.components.core.test.BeanA'.");
        new FlatFileToObjectsConverter<>(BeanA.class);
    }

    @Test
    public void testException() throws InstantiationException, IllegalAccessException {
        exception.expect(NullPointerException.class);
        new FlatFileToObjectsConverter<>(null);
    }

    @Test
    public void testDelimitedColumnRead() throws InstantiationException, IllegalAccessException {
        StringToObjectConverter spec = new FlatFileToObjectsConverter(SemicolonDelimitedBean.class);
        spec.convert(getClass().getResourceAsStream("semicolon-delimited-test-data.csv"), new TestHandle());
    }

    @Test
    public void testFixLengthColumnRead() throws InstantiationException, IllegalAccessException {
        StringToObjectConverter spec = new FlatFileToObjectsConverter(FixLengthBean.class);
        spec.convert(getClass().getResourceAsStream("fix-length-test-data.csv"), new TestHandle());
    }

    private class TestHandle implements ObjectHandle {
        @Override
        public boolean handle(Object object) {
            recCount++;
            FlatFileDataBean bean = (FlatFileDataBean) object;

            // there are 2 records in the file and the assertions check for data
            // in each
            if (recCount == 1) {
                assertFalse(bean.getActive());
            } else {
                assertTrue(bean.getActive());
            }
            return true;
        }
    }

}
