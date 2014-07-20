package ch.alv.components.web.api.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.ActionType} class.
 *
 * @since 1.0.0
 */
public class ResourceTypeTest {

    @Test
    public void testKeys() {
        assertEquals(ResourceType.STRING, ResourceType.byName("STRING"));
        assertEquals(ResourceType.NUMBER, ResourceType.byName("NUMBER"));
        assertEquals(ResourceType.INTEGER, ResourceType.byName("INTEGER"));
        assertEquals(ResourceType.BOOLEAN, ResourceType.byName("BOOLEAN"));
        assertEquals(ResourceType.DATE, ResourceType.byName("DATE"));
        assertEquals(ResourceType.FILE, ResourceType.byName("FILE"));
        assertEquals(ResourceType.OBJECT, ResourceType.byName("OBJECT"));
        assertNull(ResourceType.byName(null));
    }

    @Test
    public void testCoverageOfValues() {
        assertEquals(7, ResourceType.values().length);
    }

}
