package ch.alv.components.web.search.internal;

import ch.alv.components.web.search.RequestParamsToValuesMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.web.search.internal.WhiteListMapper} class.
 *
 * @since 1.0.0
 */
public class WhiteListMapperTest {

    @Test
    public void testMap() {
        // init source and whiteList
        Map<String, String[]> source = new HashMap<>();
        source.put("uuid", new String[]{"testUuid"});
        source.put("name", new String[]{"testName"});

        List<String> whiteList = new ArrayList<>();
        whiteList.add("name");

        // execute mut
        RequestParamsToValuesMapper strategy = new WhiteListMapper(whiteList);
        Map<String, Object> target = new HashMap<>();
        strategy.map(source, target);

        // assert state
        assertEquals(1, target.size());
        assertEquals("testName", target.get("name"));
    }

    @Test
    public void testMapNullList() {
        Map<String, String[]> source = new HashMap<>();
        source.put("uuid", new String[]{"testUuid"});
        source.put("name", new String[]{"testName"});

        // execute mut
        RequestParamsToValuesMapper strategy = new WhiteListMapper(null);
        Map<String, Object> target = new HashMap<>();
        strategy.map(source, target);

        // assert state
        assertEquals(0, target.size());
    }

}
