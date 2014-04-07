package ch.alv.components.core.utils;

import ch.alv.components.core.reflection.ReflectionUtilsException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * Unit tests for the ReflectionUtils
 *
 * @since 1.0.0
 */
public class Md5EncoderTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConvertToMd5() throws ReflectionUtilsException {
        String text = "testStringToPut39/023*{}[]//\\\\asdf";
        String correctHash = "b80bde6aa42c6b58ba659e84e668f018";
        Assert.assertEquals(correctHash, Md5Encoder.convertToMd5(text));
    }

}
