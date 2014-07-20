package ch.alv.components.web.api.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link ActionType} class.
 *
 * @since 1.0.0
 */
public class ParameterTypeTest {

    @Test
    public void testKeys() {
        assertNotNull(ParameterType.valueOf("STRING"));
        assertNotNull(ParameterType.valueOf("NUMBER"));
        assertNotNull(ParameterType.valueOf("INTEGER"));
        assertNotNull(ParameterType.valueOf("BOOLEAN"));
        assertNotNull(ParameterType.valueOf("DATE"));
        assertNotNull(ParameterType.valueOf("FILE"));
    }

    @Test
    public void testCoverageOfValues() {
        assertEquals(6, ParameterType.values().length);
    }
}
