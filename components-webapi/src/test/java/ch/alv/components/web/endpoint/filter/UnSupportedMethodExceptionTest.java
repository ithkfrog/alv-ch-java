package ch.alv.components.web.endpoint.filter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test cases for the {@link ch.alv.components.web.endpoint.filter.UnSupportedMethodException} class
 *
 * @since 1.0.0
 */
public class UnSupportedMethodExceptionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test() throws UnSupportedMethodException {
        exception.expect(UnSupportedMethodException.class);
        throw new UnSupportedMethodException();
    }



}
