package ch.alv.components.web.search.internal;

import ch.alv.components.web.search.RequestParamsToValuesMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.web.search.internal.BlackListMapper} class.
 *
 * @since 1.0.0
 */
public class BlackListMapperTest {

    @Test
    public void testMap() {
        // init source & blackList
        Map<String, String[]> source = new HashMap<>();
        source.put("uuid", new String[]{"testUuid"});
        source.put("name", new String[]{"testName"});

        List<String> blackList = new ArrayList<>();
        blackList.add("name");

        // execute mut
        RequestParamsToValuesMapper strategy = new BlackListMapper(blackList);
        Map<String, Object> target = new HashMap<>();
        strategy.map(source, target);

        // assert state
        assertEquals(1, target.size());
        assertEquals("testUuid", target.get("uuid"));
    }

    @Test
    public void testMapNullList() {
        Map<String, String[]> source = new HashMap<>();
        source.put("uuid", new String[]{"testUuid"});
        source.put("name", new String[]{"testName"});

        // execute mut
        RequestParamsToValuesMapper strategy = new BlackListMapper(null);
        Map<String, Object> target = new HashMap<>();
        strategy.map(source, target);

        // assert state
        assertEquals(2, target.size());
        assertEquals("testName", target.get("name"));
        assertEquals("testUuid", target.get("uuid"));
    }

}
