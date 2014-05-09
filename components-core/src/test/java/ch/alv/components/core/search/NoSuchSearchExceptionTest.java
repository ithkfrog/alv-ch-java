package ch.alv.components.core.search;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test for the {@link ch.alv.components.core.file.flat.reader.internal.FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
public class NoSuchSearchExceptionTest {

    private static final String MSG = "testMessage";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testWithStringParam() {
        exception.expect(NoSuchSearchException.class);
        exception.expectMessage(MSG);
        throw new NoSuchSearchException(MSG);
    }

}
