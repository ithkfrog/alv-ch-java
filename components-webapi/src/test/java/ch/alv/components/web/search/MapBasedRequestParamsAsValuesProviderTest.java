package ch.alv.components.web.search;

import ch.alv.components.web.search.internal.DefaultMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for the {@link ch.alv.components.web.search.MapBasedRequestParamsAsValuesProvider} class
 *
 * @since 1.0.0
 */
public class MapBasedRequestParamsAsValuesProviderTest {

    @Test
    public void testProvider() {
        Map<String, String[]> source = new HashMap<>();
        source.put("stringKey", new String[] { "stringValue" });
        source.put("intKey", new String[] { "77" });
        source.put("floatKey", new String[] { "77.77" });
        source.put("boolKey", new String[] { "true" });
        MapBasedRequestParamsAsValuesProvider provider = new MapBasedRequestParamsAsValuesProvider(new DefaultMapper(), source);
        assertEquals("stringValue", provider.getValues().get("stringKey"));
        assertEquals("77", provider.getValues().get("intKey"));
        assertEquals("77.77", provider.getValues().get("floatKey"));
        assertEquals("true", provider.getValues().get("boolKey"));
    }

    @Test
    public void testProviderWithoutSource() {
        MapBasedRequestParamsAsValuesProvider provider = new MapBasedRequestParamsAsValuesProvider(new DefaultMapper());
        assertNull(provider.getValues().get("stringKey"));
        assertNull(provider.getValues().get("intKey"));
        assertNull(provider.getValues().get("floatKey"));
        assertNull(provider.getValues().get("boolKey"));
    }

    @Test
    public void testSetSourceNull() {
        MapBasedRequestParamsAsValuesProvider provider = new MapBasedRequestParamsAsValuesProvider(new DefaultMapper());
        provider.setSource(null);
        assertEquals(0, provider.getValues().size());
        assertNull(provider.getValues().get("stringKey"));
    }

}
