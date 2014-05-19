package ch.alv.components.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit tests for the {@link RuntimeServiceLayerException}
 *
 * @since 1.0.0
 */
public class RuntimeServiceLayerExceptionTest {

    private static final String MSG = "Test message.";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testMsgConstructor() {
        expectedException.expect(RuntimeServiceLayerException.class);
        expectedException.expectMessage(MSG);
        throw new RuntimeServiceLayerException(MSG);
    }

    @Test
    public void testThrowableConstructor() {
        expectedException.expect(RuntimeServiceLayerException.class);
        expectedException.expectMessage(MSG);
        throw new RuntimeServiceLayerException(new IllegalStateException(MSG));
    }

    @Test
    public void testMsgAndThrowableConstructor() {
        expectedException.expect(RuntimeServiceLayerException.class);
        expectedException.expectMessage(MSG);
        throw new RuntimeServiceLayerException(MSG, new IllegalStateException());
    }

}
