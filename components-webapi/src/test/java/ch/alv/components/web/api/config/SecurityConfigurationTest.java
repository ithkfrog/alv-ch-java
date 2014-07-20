package ch.alv.components.web.api.config;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.SecurityConfiguration} class.
 *
 * @since 1.0.0
 */
public class SecurityConfigurationTest {

    private SecurityConfiguration configuration = new SecurityConfiguration();

    @Test
    public void testParameters() {
        Map<String, List<String>> parameters = new HashMap<>();
        configuration.setParameters(parameters);
        assertEquals(parameters, configuration.getParameters());
    }

    @Test
    public void testName() {
        configuration.setName("testName");
        assertEquals("testName", configuration.getName());
    }

}
