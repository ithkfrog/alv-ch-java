package ch.alv.components.web.api.provider;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for the {@link UriToJavaClassMappingTest} class.
 *
 * @since 1.0.0
 */
public class UriToJavaClassMappingTest {

    @Test
    public void testNonDefaultConstructor() {
        Map<String, Class<?>> map = new HashMap<>();
        map.put("test", UriToJavaClassMappingTest.class);
        UriToJavaClassMapping mapping = new UriToJavaClassMapping(null);
        assertEquals(0, mapping.getMapping().size());
        mapping.setMapping(map);
        assertEquals(1, mapping.getMapping().size());
        mapping = new UriToJavaClassMapping(map);
        assertEquals(1, mapping.getMapping().size());
    }

    @Test
    public void testGetClassForUri() {
        Map<String, Class<?>> map = new HashMap<>();
        map.put("test", UriToJavaClassMappingTest.class);
        UriToJavaClassMapping mapping = new UriToJavaClassMapping(map);
        assertEquals(UriToJavaClassMappingTest.class, mapping.getClassForUri("test"));
        assertNull(mapping.getClassForUri("unknown"));
    }
}
