package ch.alv.components.core.utils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


/**
 * Unit tests for the StringHelper
 *
 * @since 1.0.0
 */
public class StringHelperTest {

    private static final String STRING_TO_HASH = "testStringToPut39/023*{}[]//\\\\asdf";
    private static final String CORRECTLY_HASHED_STRING = "b80bde6aa42c6b58ba659e84e668f018";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testNotEmpty() throws ReflectionUtilsException {
        Assert.assertTrue(StringHelper.isNotEmpty("testValue"));
        Assert.assertFalse(StringHelper.isNotEmpty(""));
        Assert.assertFalse(StringHelper.isNotEmpty(null));
    }

    @Test
    public void testConvertToMd5() throws ReflectionUtilsException {
        assertEquals(CORRECTLY_HASHED_STRING, StringHelper.encodeToMd5(STRING_TO_HASH));
    }

    @Test
    public void testConvertNullToMd5() throws ReflectionUtilsException {
        exception.expect(IllegalArgumentException.class);
        StringHelper.encodeToMd5(null);
    }

    @Test
    public void testConvertToMd5WithEncoding() throws ReflectionUtilsException {
        assertEquals(CORRECTLY_HASHED_STRING, StringHelper.encodeToMd5(STRING_TO_HASH, "UTF8"));
    }

    @Test
    public void testConvertNullToMd5WithEncoding() throws ReflectionUtilsException {
        exception.expect(IllegalArgumentException.class);
        StringHelper.encodeToMd5(null, "UTF8");
    }

    @Test
    public void testConvertToSecurityHash() throws ReflectionUtilsException {
        assertEquals(CORRECTLY_HASHED_STRING, StringHelper.encodeToSecurityHash(STRING_TO_HASH, "MD5"));
    }

    @Test
    public void testConvertNullToSecurityHash() throws ReflectionUtilsException {
        exception.expect(IllegalArgumentException.class);
        StringHelper.encodeToSecurityHash(null, "MD5");
    }

    @Test
    public void testConvertToUnknownAlgorithmSecurityHash() throws ReflectionUtilsException {
        exception.expect(StringHelper.StringHelperException.class);
        exception.expectMessage("Could not convert String 'testStringToPut39/023*{}[]//\\\\asdf' to a 'UTF-8' encoded 'MD777'-hash.");
        StringHelper.encodeToSecurityHash(STRING_TO_HASH, "MD777");
    }

    @Test
    public void testConvertNonDefaultEncodingToSecurityHash() throws ReflectionUtilsException {
        exception.expect(StringHelper.StringHelperException.class);
        exception.expectMessage("Could not convert String 'testStringToPut39/023*{}[]//\\\\asdf' to a 'UTF8' encoded 'MD777'-hash.");
        StringHelper.encodeToSecurityHash(STRING_TO_HASH, "UTF8", "MD777");
    }

    @Test
    public void testConvertNullToSecurityHashWithParams() throws ReflectionUtilsException {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Source to encode must not be null");
        StringHelper.encodeToSecurityHash(null, "UTF8", "MD5");
    }

    @Test
    public void testConvertNullToSecurityHashWithEmptyParams() throws ReflectionUtilsException {
        assertEquals(CORRECTLY_HASHED_STRING, StringHelper.encodeToSecurityHash(STRING_TO_HASH, null, ""));
    }

    @Test
    public void fullCoverageForStaticTest() throws ReflectionUtilsException {
        new StringHelper();
    }

}
