package ch.alv.components.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit tests for the {@link ch.alv.components.service.RuntimeServiceLayerException}
 *
 * @since 1.0.0
 */
public class ServiceLayerExceptionTest {

    private static final String MSG = "Test message.";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testMsgConstructor() throws ServiceLayerException {
        expectedException.expect(ServiceLayerException.class);
        expectedException.expectMessage(MSG);
        throw new ServiceLayerException(MSG);
    }

    @Test
    public void testThrowableConstructor() throws ServiceLayerException {
        expectedException.expect(ServiceLayerException.class);
        expectedException.expectMessage(MSG);
        throw new ServiceLayerException(new IllegalStateException(MSG));
    }

    @Test
    public void testMsgAndThrowableConstructor() throws ServiceLayerException {
        expectedException.expect(ServiceLayerException.class);
        expectedException.expectMessage(MSG);
        throw new ServiceLayerException(MSG, new IllegalStateException());
    }

}
