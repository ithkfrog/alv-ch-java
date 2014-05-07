package ch.alv.components.web.security;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.web.security.WebAuthenticationEntryPoint} class.
 *
 * @since 1.0.0
 */
public class WebAuthenticationEntryPointTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetBeansOfType() throws IOException, ServletException {
        AuthenticationEntryPoint entryPoint = new WebAuthenticationEntryPoint();
        HttpServletResponse response = new MockHttpServletResponse();
        entryPoint.commence(new MockHttpServletRequest(), response, new AuthenticationCredentialsNotFoundException("unknown user or password."));
        assertEquals("Unauthorized", ((MockHttpServletResponse) response).getErrorMessage());
        assertEquals(401, response.getStatus());
    }

}
