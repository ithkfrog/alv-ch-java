package ch.alv.components.core.file.flat.reader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test for the {@link ch.alv.components.core.file.flat.reader.internal.FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
public class FlatFileConverterExceptionTest {

    private static final String MSG = "testMessage";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testWithStringParam() {
        exception.expect(FlatFileConverterException.class);
        exception.expectMessage(MSG);
        throw new FlatFileConverterException(MSG);
    }

    @Test
    public void testWithThrowableParam() {
        exception.expect(FlatFileConverterException.class);
        exception.expectMessage(MSG);
        throw new FlatFileConverterException(new IllegalStateException(MSG));
    }

    @Test
    public void testWithStringAndThrowableParams() {
        exception.expect(FlatFileConverterException.class);
        exception.expectMessage(MSG);
        throw new FlatFileConverterException(MSG, new IllegalStateException("Another message"));
    }

}
