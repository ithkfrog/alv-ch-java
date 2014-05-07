package ch.alv.components.web.search.internal;

import ch.alv.components.web.search.WebSearchValuesProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test cases for the {@link ch.alv.components.web.search.internal.BaseWebSearchValuesProvider} class.
 *
 * @since 1.0.0
 */
public class BaseWebSearchValuesProviderTest {

    Map<String, String[]> source = new HashMap<>();

    @Before
    public void init() {
        source.put("testSourceKey", new String[]{"testSourceValue"});
        source.put("testEmptyValueArray", new String[]{});
        source.put("testMultiValueKey", new String[]{ "testSourceValue1", "testSourceValue1" });
    }

    @Test
    public void testDefaultConstructor() {
        WebSearchValuesProvider provider = new TestValuesProvider();
        provider.setSource(source);
        executeAssertionsFor(provider);
    }

    @Test
    public void testConstructorSourceInjection() {
        executeAssertionsFor(new TestValuesProvider(source));
    }

    private void executeAssertionsFor(WebSearchValuesProvider provider) {
        assertEquals(1, provider.getValues().size());
        assertEquals("testSourceValue", provider.getStringValue("testTargetKey"));
    }

    @Test
    public void testNullConstructorSourceInjection() {
        WebSearchValuesProvider provider = new TestValuesProvider(null);
        assertEquals(1, provider.getValues().size());
        assertNull(provider.getStringValue("testTargetKey"));
    }

    @Test
    public void testSetNullSource() {
        WebSearchValuesProvider provider = new TestValuesProvider();
        provider.setSource(null);
        assertEquals(1, provider.getValues().size());
        assertNull(provider.getStringValue("testTargetKey"));
    }

    @Test
    public void testGetSingleSourceValueSuccess() {
        TestValuesProvider provider = new TestValuesProvider(source);
        assertEquals("testSourceValue", provider.getSingleSourceValue("testSourceKey", String.class));
    }

    @Test
    public void testGetSingleSourceValueNonExistingKey() {
        TestValuesProvider provider = new TestValuesProvider(source);
        assertNull(provider.getSingleSourceValue("nope", String.class));
    }

    @Test
    public void testGetSingleSourceValueEmptyArray() {
        TestValuesProvider provider = new TestValuesProvider(source);
        assertNull(provider.getSingleSourceValue("testEmptyValueArray", String.class));
    }

    @Test
    public void testGetMultiSourceValueSuccess() {
        TestValuesProvider provider = new TestValuesProvider(source);
        List<String> list = provider.getMultiSourceValue("testMultiValueKey", String.class);
        assertEquals(2, list.size());
    }

    @Test
    public void testGetMultiSourceValueNullKey() {
        TestValuesProvider provider = new TestValuesProvider(source);
        assertNull(provider.getMultiSourceValue(null, String.class));
    }

    @Test
    public void testGetMultiSourceValueNonExistingKey() {
        TestValuesProvider provider = new TestValuesProvider(source);
        assertNull(provider.getMultiSourceValue("nope", String.class));
    }

    @Test
    public void testGetMultiSourceValueNullTargetClass() {
        TestValuesProvider provider = new TestValuesProvider(source);
        assertNull(provider.getMultiSourceValue("testMultiValueKey", null));
    }

    @Test
    public void testGetMultiSourceValueEmptyArray() {
        TestValuesProvider provider = new TestValuesProvider(source);
        assertNull(provider.getMultiSourceValue("testEmptyValueArray", String.class));
    }

    private class TestValuesProvider extends BaseWebSearchValuesProvider {

        protected TestValuesProvider() {
            super();
        }

        protected TestValuesProvider(Map<String, String[]> source) {
            super(source);
        }

        @Override
        protected void putData() {
            values.put("testTargetKey", getStringSourceValue("testSourceKey"));
        }

        protected <T> T exposeGetSingleSourceValue(String key, Class<T> type) {
            return super.getSingleSourceValue(key, type);
        }

        protected <T> List<T> exposeGetMultiSourceValue(String key, Class<T> type) {
            return super.getMultiSourceValue(key, type);
        }
    }


}
