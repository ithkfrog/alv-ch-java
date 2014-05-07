package ch.alv.components.core.utils;

import org.junit.Assert;
import org.junit.Test;


/**
 * Unit tests for the ReflectionUtils
 *
 * @since 1.0.0
 */
public class ConversionUtilsTest {

    public static final String VALUE_INT_LONG = "233";
    public static final String VALUE_FLOAT_DOUBLE = "233.3335";
    public static final String VALUE_STRING = "testString";
    public static final String UNCONVERTIBLE_NOPE = "nope";

    @Test
    public void testConvertToString() throws ReflectionUtilsException {
        String text = VALUE_STRING;
        Assert.assertEquals(text, ConversionUtils.convert(text, String.class));
    }

    @Test
    public void testConvertToSimpleInt() throws ReflectionUtilsException {
        String text = VALUE_INT_LONG;
        Assert.assertEquals(Integer.valueOf(text), ConversionUtils.convert(text, Integer.class));
    }

    @Test
    public void testConvertToObjectInt() throws ReflectionUtilsException {
        String text = VALUE_INT_LONG;
        Assert.assertEquals(Integer.valueOf(text), ConversionUtils.convert(text, int.class));
    }

    @Test
    public void testConvertToIntDefaultOnError() throws ReflectionUtilsException {
        String text = VALUE_STRING;
        Assert.assertEquals(Integer.valueOf(0), ConversionUtils.convert(text, Integer.class));
    }

    @Test
    public void testConvertToSimpleLong() throws ReflectionUtilsException {
        String text = VALUE_INT_LONG;
        Assert.assertEquals(Long.valueOf(text), ConversionUtils.convert(text, long.class));
    }

    @Test
    public void testConvertToObjectLong() throws ReflectionUtilsException {
        String text = VALUE_INT_LONG;
        Assert.assertEquals(Long.valueOf(text), ConversionUtils.convert(text, Long.class));
    }

    @Test
    public void testConvertToLongDefaultOnError() throws ReflectionUtilsException {
        String text = VALUE_STRING;
        Assert.assertEquals(Long.valueOf(0), ConversionUtils.convert(text, Long.class));
    }

    @Test
    public void testConvertToSimpleFloat() throws ReflectionUtilsException {
        String text = VALUE_FLOAT_DOUBLE;
        Assert.assertEquals(Float.valueOf(text), ConversionUtils.convert(text, float.class));
    }

    @Test
    public void testConvertToObjectFloat() throws ReflectionUtilsException {
        String text = VALUE_FLOAT_DOUBLE;
        Assert.assertEquals(Float.valueOf(text), ConversionUtils.convert(text, Float.class));
    }

    @Test
    public void testConvertToFloatDefaultOnError() throws ReflectionUtilsException {
        String text = VALUE_STRING;
        Assert.assertEquals(Float.valueOf(0), ConversionUtils.convert(text, Float.class));
    }

    @Test
    public void testConvertToSimpleDouble() throws ReflectionUtilsException {
        String text = VALUE_FLOAT_DOUBLE;
        Assert.assertEquals(Double.valueOf(text), ConversionUtils.convert(text, double.class));
    }

    @Test
    public void testConvertToObjectDouble() throws ReflectionUtilsException {
        String text = VALUE_FLOAT_DOUBLE;
        Assert.assertEquals(Double.valueOf(text), ConversionUtils.convert(text, Double.class));
    }

    @Test
    public void testConvertToDoubleDefaultOnError() throws ReflectionUtilsException {
        String text = VALUE_STRING;
        Assert.assertEquals(Double.valueOf(0), ConversionUtils.convert(text, Double.class));
    }


    @Test
    public void testConvertToSimpleBoolean() throws ReflectionUtilsException {
        String text = "true";
        Assert.assertEquals(Boolean.valueOf(text), ConversionUtils.convert(text, boolean.class));
    }

    @Test
    public void testConvertToObjectBoolean() throws ReflectionUtilsException {
        String text = "true";
        Assert.assertEquals(Boolean.valueOf(text), ConversionUtils.convert(text, Boolean.class));
    }

    @Test
    public void testConvertToBooleanDefaultOnError() throws ReflectionUtilsException {
        String text = VALUE_STRING;
        Assert.assertEquals(Boolean.FALSE, ConversionUtils.convert(text, boolean.class));
    }

    @Test
    public void testUnknownType() throws ReflectionUtilsException {
        String text = VALUE_STRING;
        Assert.assertEquals(null, ConversionUtils.convert(text, ConversionUtilsTest.class));
    }


    @Test
    public void testConvertToStringWithDefault() throws ReflectionUtilsException {
        String text = VALUE_STRING;
        Assert.assertEquals(text, ConversionUtils.convert(null, String.class, text));
    }

    @Test
    public void testConvertNullToStringWithDefault() throws ReflectionUtilsException {
        String text = VALUE_STRING;
        Assert.assertEquals(text, ConversionUtils.convert(text, String.class, ""));
    }

    @Test
    public void testConvertToSimpleIntWithDefault() throws ReflectionUtilsException {
        String text = VALUE_INT_LONG;
        Assert.assertEquals(Integer.valueOf(text), ConversionUtils.convert(UNCONVERTIBLE_NOPE, Integer.class, 233));
    }

    @Test
    public void testConvertToObjectIntWithDefault() throws ReflectionUtilsException {
        String text = VALUE_INT_LONG;
        Assert.assertEquals(Integer.valueOf(text), ConversionUtils.convert(UNCONVERTIBLE_NOPE, int.class, 233));
    }

    @Test
    public void testConvertToSimpleLongWithDefault() throws ReflectionUtilsException {
        String text = VALUE_INT_LONG;
        Assert.assertEquals(Long.valueOf(text), ConversionUtils.convert(UNCONVERTIBLE_NOPE, long.class, 233L));
    }

    @Test
    public void testConvertToObjectLongWithDefault() throws ReflectionUtilsException {
        String text = VALUE_INT_LONG;
        Assert.assertEquals(Long.valueOf(text), ConversionUtils.convert(UNCONVERTIBLE_NOPE, Long.class, 233L));
    }

    @Test
    public void testConvertToSimpleFloatWithDefault() throws ReflectionUtilsException {
        String text = VALUE_FLOAT_DOUBLE;
        Assert.assertEquals(Float.valueOf(text), ConversionUtils.convert(UNCONVERTIBLE_NOPE, float.class, 233.3335F));
    }

    @Test
    public void testConvertToObjectFloatWithDefault() throws ReflectionUtilsException {
        String text = VALUE_FLOAT_DOUBLE;
        Assert.assertEquals(Float.valueOf(text), ConversionUtils.convert(UNCONVERTIBLE_NOPE, Float.class, 233.3335F));
    }

    @Test
    public void testConvertToSimpleDoubleWithDefault() throws ReflectionUtilsException {
        String text = VALUE_FLOAT_DOUBLE;
        Assert.assertEquals(Double.valueOf(text), ConversionUtils.convert(UNCONVERTIBLE_NOPE, double.class, 233.3335));
    }

    @Test
    public void testConvertToObjectDoubleWithDefault() throws ReflectionUtilsException {
        String text = VALUE_FLOAT_DOUBLE;
        Assert.assertEquals(Double.valueOf(text), ConversionUtils.convert(UNCONVERTIBLE_NOPE, Double.class, 233.3335));
    }

    @Test
    public void testConvertToSimpleBooleanWithDefault() throws ReflectionUtilsException {
        Assert.assertEquals(true, ConversionUtils.convert(UNCONVERTIBLE_NOPE, boolean.class, true));
    }

    @Test
    public void testConvertToObjectBooleanWithDefault() throws ReflectionUtilsException {
        Assert.assertEquals(true, ConversionUtils.convert(UNCONVERTIBLE_NOPE, Boolean.class, true));
    }

    @Test
    public void testConvertToObjectUnknownClass() throws ReflectionUtilsException {
        Assert.assertEquals(true, ConversionUtils.convert(UNCONVERTIBLE_NOPE, ConversionUtilsTest.class, true));
    }

    @Test
    public void testToBooleanTrue() throws ReflectionUtilsException {
        Assert.assertEquals(true, ConversionUtils.toBooleanValue("true", true));
        Assert.assertEquals(true, ConversionUtils.toBooleanValue("1", true));
    }

    @Test
    public void testToBooleanFalse() throws ReflectionUtilsException {
        Assert.assertEquals(false, ConversionUtils.toBooleanValue("false", false));
        Assert.assertEquals(false, ConversionUtils.toBooleanValue("0", false));
    }

    @Test
    public void testToBooleanEmpty() throws ReflectionUtilsException {
        Assert.assertEquals(true, ConversionUtils.toBooleanValue("", true));
    }

    @Test
    public void fullCoverageForStaticTest() {
        new ConversionUtils();
    }


}
