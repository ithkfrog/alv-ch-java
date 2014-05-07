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
public class StringBlackListWebSearchValuesProviderTest {

    Map<String, String[]> source = new HashMap<>();

    List<String> blackList = new ArrayList<>();

    @Before
    public void init() {
        source.put("blacklisted_one", new String[]{"blacklisted_one_value"});
        source.put("blacklisted_two", new String[]{"blacklisted_two_value"});
        source.put("ok_one", new String[]{"ok_one_value"});
        source.put("ok_two", new String[]{"ok_two_value"});

        blackList.add("blacklisted_one");
        blackList.add("blacklisted_two");
    }

    @Test
    public void testWithData() {
        WebSearchValuesProvider provider = new StringBlackListWebValuesProvider(source, blackList);
        assertEquals(2, provider.getValues().size());
        assertEquals("ok_one_value", provider.getStringValue("ok_one"));
        assertEquals("ok_two_value", provider.getStringValue("ok_two"));
        assertNull(provider.getStringValue("blacklisted_one"));
        assertNull(provider.getStringValue("blacklisted_two"));
    }

    @Test
    public void testGetBlacklist() {
        StringBlackListWebValuesProvider provider = new StringBlackListWebValuesProvider(source, blackList);
        assertEquals(2, provider.getBlacklist().size());
    }

    @Test
    public void testWithoutData() {
        source.clear();
        WebSearchValuesProvider provider = new StringBlackListWebValuesProvider(source, blackList);
        assertEquals(0, provider.getValues().size());
        assertNull(provider.getStringValue("ok_one"));
        assertNull(provider.getIntValue("ok_two"));
        assertNull(provider.getStringValue("blacklisted_one"));
        assertNull(provider.getStringValue("blacklisted_two"));
    }

    @Test
    public void testNullList() {
        WebSearchValuesProvider provider = new StringBlackListWebValuesProvider(source, null);
        assertEquals(4, provider.getValues().size());
    }
}
