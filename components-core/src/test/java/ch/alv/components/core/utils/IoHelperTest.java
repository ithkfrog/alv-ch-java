package ch.alv.components.core.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


/**
 * Unit tests for the {@link IoHelper} class.
 *
 * @since 1.0.0
 */
public class IoHelperTest {

    public static final String FILE_NAME = "io-helper-test-file.csv";
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testDefaultConstructor() {
        IoHelper helper = new IoHelper();
        assertNotNull(helper);
    }

    @Test
    public void testCloseReaderQuietly() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILE_NAME)));
        IoHelper.closeReaderQuietly(br);
        exception.expect(IOException.class);
        exception.expectMessage("Stream closed");
        br.read();
    }

    @Test
    public void testCloseReaderQuietlyIdempotence() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILE_NAME)));
        IoHelper.closeReaderQuietly(br);
        IoHelper.closeReaderQuietly(br);
        IoHelper.closeReaderQuietly(br);
        IoHelper.closeReaderQuietly(br);
    }

    @Test
    public void testCloseReaderQuietlyWithException() {
        IoHelper.closeReaderQuietly(null);
    }

    @Test
    public void testReadLineSilently() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILE_NAME)));
        reader.close();
        IoHelper.readLineSilently(reader);
        assertNull(IoHelper.readLineSilently(reader));
        assertNull(IoHelper.readLineSilently(null));
    }

}
