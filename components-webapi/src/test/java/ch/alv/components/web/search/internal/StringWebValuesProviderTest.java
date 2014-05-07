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
public class StringWebValuesProviderTest {

    Map<String, String[]> source = new HashMap<>();

    @Before
    public void init() {
        source.put("uuid", new String[]{"testUuid"});
        source.put("version", new String[]{"1"});
    }

    @Test
    public void testWithData() {
        WebSearchValuesProvider provider = new StringWebValuesProvider(source);
        assertEquals(2, provider.getValues().size());
        assertEquals("testUuid", provider.getStringValue("uuid"));
        assertEquals("1", provider.getStringValue("version"));
    }

    @Test
    public void testWithoutData() {
        source.clear();
        WebSearchValuesProvider provider = new StringWebValuesProvider(source);
        assertEquals(0, provider.getValues().size());
        assertNull(provider.getStringValue("uuid"));
        assertNull(provider.getStringValue("version"));
    }





}
