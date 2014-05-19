package ch.alv.components.data.query;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit tests for the {@link ch.alv.components.data.DataLayerException}
 *
 * @since 1.0.0
 */
public class NoSuchQueryProviderExceptionTest {

    private static final String MSG = "test";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testMsgConstructor() throws NoSuchQueryProviderException {
        expectedException.expect(NoSuchQueryProviderException.class);
        expectedException.expectMessage("Could not find a QueryProvider with name 'test'. Execution aborted.");
        throw new NoSuchQueryProviderException(MSG);
    }

}