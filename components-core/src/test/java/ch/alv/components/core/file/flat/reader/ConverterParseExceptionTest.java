package ch.alv.components.core.file.flat.reader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test for the {@link ch.alv.components.core.file.flat.reader.internal.FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
public class ConverterParseExceptionTest {

    private static final String MSG = "testMessage";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testWithStringParam() {
        exception.expect(ConverterParseException.class);
        exception.expectMessage(MSG);
        throw new ConverterParseException(MSG);
    }

    @Test
    public void testException() {
        exception.expect(ConverterParseException.class);
        exception.expectMessage(MSG);
        throw new ConverterParseException(new IllegalStateException(MSG));
    }

}
