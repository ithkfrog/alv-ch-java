package ch.alv.components.web.api.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.ActionType} class.
 *
 * @since 1.0.0
 */
public class ActionTypeTest {

    @Test
    public void testKeys() {
        assertNotNull(ActionType.valueOf("GET"));
        assertNotNull(ActionType.valueOf("POST"));
        assertNotNull(ActionType.valueOf("PUT"));
        assertNotNull(ActionType.valueOf("DELETE"));
        assertNotNull(ActionType.valueOf("HEAD"));
        assertNotNull(ActionType.valueOf("PATCH"));
        assertNotNull(ActionType.valueOf("TRACE"));
        assertNotNull(ActionType.valueOf("OPTIONS"));
    }

    @Test
    public void testCoverageOfValues() {
        assertEquals(8, ActionType.values().length);
    }
}
