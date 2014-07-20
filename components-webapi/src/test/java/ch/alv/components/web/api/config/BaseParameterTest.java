package ch.alv.components.web.api.config;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.ResourceConfiguration} class.
 *
 * @since 1.0.0
 */
public class BaseParameterTest {



    private BaseParameter param = new BaseParameterTestHelper();

    private String name = "testName";
    private String example = "testExample";
    private ParameterType parameterType = ParameterType.STRING;
    private boolean required = true;

    @Test
    public void testParamConstructor() {
        BaseParameter parameter = new BaseParameterTestHelper(name, parameterType, required);
        assertEquals(name, parameter.getName());
        assertEquals(ParameterType.STRING, parameter.getType());
        assertEquals(required, parameter.isRequired());
    }

    @Test
    public void testName() {
        param.setName(name);
        assertEquals(name, param.getName());
    }

    @Test
    public void testExample() {
        param.setExample(example);
        assertEquals(example, param.getExample());
    }

    @Test
    public void testRepeat() {
        param.setRepeat(true);
        assertTrue(param.isRepeat());
    }

    @Test
    public void testDefaultValue() {
        param.setDefaultValue("value");
        assertEquals("value", param.getDefaultValue());
    }

    @Test
    public void testEnumeration() {
        List<String> enumeration = new ArrayList<>();
        param.setEnumeration(enumeration);
        assertEquals(enumeration, param.getEnumeration());
    }

    @Test
    public void testMinimum() {
        param.setMinimum(new BigDecimal(11));
        assertEquals(new BigDecimal(11), param.getMinimum());
    }

    @Test
    public void testMaximum() {
        param.setMaximum(new BigDecimal(88));
        assertEquals(new BigDecimal(88), param.getMaximum());
    }

    @Test
    public void testMinLength() {
        param.setMinLength(5);
        assertEquals((Integer) 5, param.getMinLength());
    }

    @Test
    public void testMaxLength() {
        param.setMaxLength(77);
        assertEquals((Integer) 77, param.getMaxLength());
    }

    private class BaseParameterTestHelper extends BaseParameter {
        private static final long serialVersionUID = -1729010725602766030L;

        private BaseParameterTestHelper() { }
        private BaseParameterTestHelper(String name, ParameterType type, boolean required) { super(name, type, required); }
    }

}
