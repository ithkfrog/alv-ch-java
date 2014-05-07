package ch.alv.components.web.endpoint.filter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test cases for the {@link UnauthorizedException} class
 *
 * @since 1.0.0
 */
public class UnauthorizedExceptionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test() throws UnauthorizedException {
        exception.expect(UnauthorizedException.class);
        throw new UnauthorizedException();
    }



}
