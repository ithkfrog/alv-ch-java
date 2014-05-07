package ch.alv.components.web.search.internal;

import ch.alv.components.web.search.WebSearchValuesProvider;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
public class StringWhiteListWebSearchValuesProviderTest {

    Map<String, String[]> source = new HashMap<>();

    List<String> whiteList = new ArrayList<>();

    @Before
    public void init() {
        source.put("whitelisted_one", new String[]{"whitelisted_one_value"});
        source.put("whitelisted_two", new String[]{"whitelisted_two_value"});
        source.put("nok_one", new String[]{"nok_one_value"});
        source.put("nok_two", new String[]{"nok_two_value"});

        whiteList.add("whitelisted_one");
        whiteList.add("whitelisted_two");
    }

    @Test
    public void testWithData() {
        WebSearchValuesProvider provider = new StringWhiteListWebValuesProvider(source, whiteList);
        assertEquals(2, provider.getValues().size());
        assertEquals("whitelisted_one_value", provider.getStringValue("whitelisted_one"));
        assertEquals("whitelisted_two_value", provider.getStringValue("whitelisted_two"));
        assertNull(provider.getStringValue("nok_one"));
        assertNull(provider.getStringValue("nok_two"));
    }

    @Test
    public void testGetBlacklist() {
        StringWhiteListWebValuesProvider provider = new StringWhiteListWebValuesProvider(source, whiteList);
        assertEquals(2, provider.getWhiteList().size());
    }

    @Test
    public void testWithoutData() {
        source.clear();
        WebSearchValuesProvider provider = new StringWhiteListWebValuesProvider(source, whiteList);
        assertEquals(0, provider.getValues().size());
        assertNull(provider.getStringValue("nok_one"));
        assertNull(provider.getIntValue("nok_two"));
        assertNull(provider.getStringValue("whitelisted_one"));
        assertNull(provider.getStringValue("whitelisted_two"));
    }

    @Test
    public void testNullList() {
        WebSearchValuesProvider provider = new StringWhiteListWebValuesProvider(source, null);
        assertEquals(0, provider.getValues().size());
    }

}
