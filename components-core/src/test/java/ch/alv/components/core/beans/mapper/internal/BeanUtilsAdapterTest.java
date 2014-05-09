package ch.alv.components.core.beans.mapper.internal;

import ch.alv.components.core.beans.Identifiable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link BeanUtilsAdapter} class.
 *
 * @since 1.0.0
 */
public class BeanUtilsAdapterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Identifiable<String> entity = new MockIdentifiable();

    @Test
    public void testPopulateQuietly() {
        Map<String, Object> values = new HashMap<>();
        values.put("id", "testId");
        BeanUtilsAdapter.populateQuietly(entity, values);
        assertEquals("testId", entity.getId());
    }

    @Test
    public void testPopulateQuietlyInternalException() throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> values = new HashMap<>();
        values.put("id", "testId");
        ErrorProneMockIdentifiable testObject = new ErrorProneMockIdentifiable();
        BeanUtilsAdapter.populateQuietly(testObject, values);
        assertEquals("testId", testObject.getId());
    }

    @Test
    public void testConstructor()  {
        new BeanUtilsAdapter();
    }

    public class MockIdentifiable implements Identifiable<String> {
        private String id;
        @Override
        public String getId() {
            return id;
        }
        @Override
        public void setId(String id) {
            this.id = id;
        }
    }

    public class ErrorProneMockIdentifiable implements Identifiable<String> {
        private String id;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
            throw new IllegalStateException();
        }
    }

}
