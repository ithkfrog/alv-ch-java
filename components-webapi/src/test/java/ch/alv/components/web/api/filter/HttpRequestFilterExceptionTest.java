package ch.alv.components.web.api.filter;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link HttpRequestFilterExceptionTest} class.
 *
 * @since 1.0.0
 */
public class HttpRequestFilterExceptionTest {

    public static final String TEST_MSG = "testMsg";
    public static final HttpStatus HTTP_STATUS = HttpStatus.ACCEPTED;

    private HttpRequestFilterException exception = new HttpRequestFilterException(TEST_MSG, HTTP_STATUS);

    @Test
    public void testMsg() {
        assertEquals(TEST_MSG, exception.getMessage());
    }

    @Test
    public void testStatus() {
        assertEquals(HTTP_STATUS, exception.getStatus());
    }
}
