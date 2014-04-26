package ch.alv.components.core.file.flat.reader.internal;

import ch.alv.components.core.file.flat.reader.ConverterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Test for the {@link FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
public class FlatFileRecordTest {

    public static final String TEST_NAME = "testName";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConstructor() {
        assertEquals(TEST_NAME, new FlatFileRecord(TEST_NAME).getName());
    }

    @Test
    public void testConstructorException() {
        exception.expect(ConverterException.class);
        exception.expectMessage("Record name must be specified");
        new FlatFileRecord("");
    }

}
