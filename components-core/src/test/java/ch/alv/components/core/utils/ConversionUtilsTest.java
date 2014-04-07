package ch.alv.components.core.utils;

import ch.alv.components.core.reflection.ReflectionUtilsException;
import org.junit.Assert;
import org.junit.Test;


/**
 * Unit tests for the ReflectionUtils
 *
 * @since 1.0.0
 */
public class ConversionUtilsTest {

    @Test
    public void testConvertToString() throws ReflectionUtilsException {
        String text = "testString";
        Assert.assertEquals(text, ConversionUtils.convert(text, String.class));
    }

    @Test
    public void testConvertToInt() throws ReflectionUtilsException {
        String text = "233";
        Assert.assertEquals(Integer.valueOf(text), ConversionUtils.convert(text, Integer.class));
    }

    @Test
    public void testConvertToIntDefaultOnError() throws ReflectionUtilsException {
        String text = "testString";
        Assert.assertEquals(Integer.valueOf(0), ConversionUtils.convert(text, Integer.class));
    }

    @Test
    public void testConvertToLong() throws ReflectionUtilsException {
        String text = "233";
        Assert.assertEquals(Long.valueOf(text), ConversionUtils.convert(text, Long.class));
    }

    @Test
    public void testConvertToLongDefaultOnError() throws ReflectionUtilsException {
        String text = "testString";
        Assert.assertEquals(Long.valueOf(0), ConversionUtils.convert(text, Long.class));
    }

    @Test
    public void testConvertToFloat() throws ReflectionUtilsException {
        String text = "233.3335";
        Assert.assertEquals(Float.valueOf(text), ConversionUtils.convert(text, Float.class));
    }

    @Test
    public void testConvertToFloatDefaultOnError() throws ReflectionUtilsException {
        String text = "testString";
        Assert.assertEquals(Float.valueOf(0), ConversionUtils.convert(text, Float.class));
    }

    @Test
    public void testConvertToDouble() throws ReflectionUtilsException {
        String text = "233.3335";
        Assert.assertEquals(Double.valueOf(text), ConversionUtils.convert(text, Double.class));
    }

    @Test
    public void testConvertToDoubleDefaultOnError() throws ReflectionUtilsException {
        String text = "testString";
        Assert.assertEquals(Double.valueOf(0), ConversionUtils.convert(text, Double.class));
    }


    @Test
    public void testConvertToBoolean() throws ReflectionUtilsException {
        String text = "true";
        Assert.assertEquals(Boolean.valueOf(text), ConversionUtils.convert(text, Boolean.class));
    }

    @Test
    public void testConvertToBooleanDefaultOnError() throws ReflectionUtilsException {
        String text = "testString";
        Assert.assertEquals(Boolean.FALSE, ConversionUtils.convert(text, Boolean.class));
    }

}
