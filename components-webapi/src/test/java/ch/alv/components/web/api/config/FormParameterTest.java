package ch.alv.components.web.api.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link FormParameter} class.
 *
 * @since 1.0.0
 */
public class FormParameterTest {

    @Test
    public void testDefaultConstructor() {
        assertNotNull(new FormParameter());
    }

    @Test
    public void testParamConstructor() {
        String name = "testName";
        boolean required = true;
        FormParameter parameter = new FormParameter(name, ParameterType.STRING, required);
        assertEquals(name, parameter.getName());
        assertEquals(ParameterType.STRING, parameter.getType());
        assertEquals(required, parameter.isRequired());
    }

}
