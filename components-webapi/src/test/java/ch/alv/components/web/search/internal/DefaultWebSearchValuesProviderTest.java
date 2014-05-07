package ch.alv.components.web.search.internal;

import ch.alv.components.web.search.WebSearchValuesProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test cases for the {@link ch.alv.components.web.search.internal.BaseWebSearchValuesProvider} class.
 *
 * @since 1.0.0
 */
public class DefaultWebSearchValuesProviderTest {

    Map<String, String[]> source = new HashMap<>();

    @Before
    public void init() {
        source.put("uuid", new String[]{"testUuid"});
        source.put("version", new String[]{"1"});
        source.put("name", new String[]{"testName"});
        source.put("testSourceKey", new String[]{"testSourceValue"});
    }

    @Test
    public void testWithData() {
        WebSearchValuesProvider provider = new DefaultWebSearchValuesProvider(source);
        assertEquals(3, provider.getValues().size());
        assertEquals("testUuid", provider.getStringValue("uuid"));
        assertEquals((Integer) 1, provider.getIntValue("version"));
        assertEquals("testName", provider.getStringValue("name"));
        assertNull(provider.getStringValue("testSourceKey"));
    }

    @Test
    public void testWithoutData() {
        source.clear();
        WebSearchValuesProvider provider = new DefaultWebSearchValuesProvider(source);
        assertEquals(0, provider.getValues().size());
        assertNull(provider.getStringValue("uuid"));
        assertNull(provider.getIntValue("version"));
        assertNull(provider.getStringValue("name"));
        assertNull(provider.getStringValue("testSourceKey"));
    }





}
