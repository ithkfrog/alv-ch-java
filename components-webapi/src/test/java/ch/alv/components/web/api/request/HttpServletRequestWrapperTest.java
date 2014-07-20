package ch.alv.components.web.api.request;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link HttpServletRequestWrapper} class.
 *
 * @since 1.0.0
 */
public class HttpServletRequestWrapperTest {

    private MockHttpServletRequest request = new MockHttpServletRequest();

    @Test
    public void testCorrelationId() {
        assertNotNull(new HttpServletRequestWrapper(request).getCorrelationId());
    }

    @Test
    public void testRequest() {
        assertNotNull(new HttpServletRequestWrapper(request).getRequest());
    }
}
