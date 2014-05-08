package ch.alv.components.web.search.internal;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.web.search.internal.DefaultMapper} class.
 *
 * @since 1.0.0
 */
public class DefaultMapperTest {

    @Test
    public void testMap() {
        // init source
        Map<String, String[]> source = new HashMap<>();
        source.put("uuid", new String[]{"testUuid"});
        source.put("name", new String[]{"testName"});

        // execute mut
        DefaultMapper strategy = new DefaultMapper();
        Map<String, Object> target = new HashMap<>();
        strategy.map(source, target);

        // assert state
        assertEquals(2, target.size());
        assertEquals("testUuid", target.get("uuid"));
        assertEquals("testName", target.get("name"));
    }

}
