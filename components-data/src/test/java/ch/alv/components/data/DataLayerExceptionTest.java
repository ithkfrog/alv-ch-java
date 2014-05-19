package ch.alv.components.data;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit tests for the {@link DataLayerException}
 *
 * @since 1.0.0
 */
public class DataLayerExceptionTest {

    private static final String MSG = "Test message.";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testMsgConstructor() throws DataLayerException {
        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MSG);
        throw new DataLayerException(MSG);
    }

    @Test
    public void testThrowableConstructor() throws DataLayerException {
        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MSG);
        throw new DataLayerException(new IllegalStateException(MSG));
    }

    @Test
    public void testMsgAndThrowableConstructor() throws DataLayerException {
        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MSG);
        throw new DataLayerException(MSG, new IllegalStateException());
    }

}
