package ch.alv.components.core.file.flat.reader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test cases for the {@link FlatFileColumnSeparatorType} enum.
 *
 * @since 1.0.0
 */
public class FlatFileColumnSeparatorTypeTest {

    @Test
    public void testKeys() {
        assertNotNull(FlatFileColumnSeparatorType.CHARACTER);
        assertNotNull(FlatFileColumnSeparatorType.FIXLENGTH);
    }

    @Test
    public void testCoverageOfValues() {
        assertEquals(2, FlatFileColumnSeparatorType.values().length);
    }

}
