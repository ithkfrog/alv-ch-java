package ch.alv.components.core.utils;

import ch.alv.components.core.reflection.ReflectionUtilsException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * Unit tests for the StringHelper
 *
 * @since 1.0.0
 */
public class StringHelperTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConvertToMd5() throws ReflectionUtilsException {
        Assert.assertTrue(StringHelper.isNotEmpty("testValue"));
        Assert.assertFalse(StringHelper.isNotEmpty(""));
        Assert.assertFalse(StringHelper.isNotEmpty(null));
    }

}
