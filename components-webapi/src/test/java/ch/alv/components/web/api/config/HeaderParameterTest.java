package ch.alv.components.web.api.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.HeaderParameter} class.
 *
 * @since 1.0.0
 */
public class HeaderParameterTest {

    @Test
    public void testDefaultConstructor() {
        assertNotNull(new HeaderParameter());
    }

    @Test
    public void testParamConstructor() {
        String name = "testName";
        boolean required = true;
        HeaderParameter parameter = new HeaderParameter(name, ParameterType.STRING, required);
        assertEquals(name, parameter.getName());
        assertEquals(ParameterType.STRING, parameter.getType());
        assertEquals(required, parameter.isRequired());
    }

}
