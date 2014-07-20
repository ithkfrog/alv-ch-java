package ch.alv.components.web.api.config;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the {@link ParameterValidator} class.
 *
 * @since 1.0.0
 */
public class ParameterValidatorTest {

    @Test
    public void testDefaultConstructor() {
        new ParameterValidator();
    }

    @Test
    public void testValidateString() {
        QueryParameter param = new QueryParameter();
        param.setType(ParameterType.STRING);

        assertTrue(ParameterValidator.validate(param, "valid"));

        param.setMaxLength(5);
        param.setMinLength(4);
        param.setPattern("[inv]?.*");
        assertFalse(ParameterValidator.validate(param, "invalid"));
        assertFalse(ParameterValidator.validate(param, "in"));
        assertFalse(ParameterValidator.validate(param, "inv"));
        assertTrue(ParameterValidator.validate(param, "inva"));
        assertFalse(ParameterValidator.validate(param, "invalidLength"));
        assertTrue(ParameterValidator.validate(param, "valid"));

        param.setMaxLength(null);
        param.setMinLength(null);
        param.setPattern(null);
        param.getEnumeration().add("valid");
        assertTrue(ParameterValidator.validate(param, "valid"));
        assertFalse(ParameterValidator.validate(param, "invalid"));

        param.setPattern("[test]*Pattern");
        param.setEnumeration(new ArrayList<String>());
        assertTrue(ParameterValidator.validateString(null, ""));
        assertFalse(ParameterValidator.validateString(param, "invalid"));

    }

    @Test
    public void testValidateNumber() {
        QueryParameter param = new QueryParameter();
        param.setType(ParameterType.NUMBER);
        assertTrue(ParameterValidator.validate(param, "99.899"));
        param.setMinimum(new BigDecimal("99.9"));
        param.setMaximum(new BigDecimal("100.1"));
        assertFalse(ParameterValidator.validate(param, "invalid"));
        assertFalse(ParameterValidator.validate(param, "99.899"));
        assertFalse(ParameterValidator.validate(param, "100.101"));
        assertTrue(ParameterValidator.validate(param, "99.9"));
        assertTrue(ParameterValidator.validate(param, "100.0"));
        assertTrue(ParameterValidator.validate(param, "100.1"));

        assertTrue(ParameterValidator.validateNumber(null, "any"));
    }

    @Test
    public void testValidateInteger() {
        QueryParameter param = new QueryParameter();
        param.setType(ParameterType.INTEGER);

        assertTrue(ParameterValidator.validateInteger(param, "98"));
        assertTrue(ParameterValidator.validateInteger(null, "1"));

        param.setMinimum(new BigDecimal("99"));
        param.setMaximum(new BigDecimal("101"));
        assertFalse(ParameterValidator.validate(param, "invalid"));
        assertFalse(ParameterValidator.validate(param, "100.01"));
        assertFalse(ParameterValidator.validate(param, "102"));
        assertFalse(ParameterValidator.validateInteger(param, "98"));
        assertTrue(ParameterValidator.validate(param, "99"));
        assertTrue(ParameterValidator.validate(param, "100"));
        assertTrue(ParameterValidator.validate(param, "101"));

    }

    @Test
    public void testValidateDate() {
        QueryParameter param = new QueryParameter();
        param.setType(ParameterType.DATE);
        assertTrue(ParameterValidator.validate(param, "2014-01-01"));
    }

    @Test
    public void testValidateFile() {
        QueryParameter param = new QueryParameter();
        param.setType(ParameterType.FILE);
        assertTrue(ParameterValidator.validate(param, ""));
    }

    @Test
    public void testValidateBoolean() {
        QueryParameter param = new QueryParameter();
        param.setType(ParameterType.BOOLEAN);
        assertFalse(ParameterValidator.validate(param, "invalid"));
        assertTrue(ParameterValidator.validate(param, "true"));
        assertTrue(ParameterValidator.validate(param, "TRUE"));
        assertTrue(ParameterValidator.validate(param, "false"));
        assertTrue(ParameterValidator.validate(param, "FALSE"));
    }

    @Test
    public void testNullType() {
        QueryParameter param = new QueryParameter("test", null, false);
        assertFalse(ParameterValidator.validate(param, "invalid"));
    }

    @Test
    public void testNullParam() {
        assertFalse(ParameterValidator.validate(null, "invalid"));
    }
}
