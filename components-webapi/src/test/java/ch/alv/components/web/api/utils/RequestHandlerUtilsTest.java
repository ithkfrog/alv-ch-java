package ch.alv.components.web.api.utils;

import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link RequestHandlerUtils} class.
 *
 * @since 1.0.0
 */
public class RequestHandlerUtilsTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConstructor() {
        assertNotNull(new RequestHandlerUtils());
    }

    @Test
    public void testSuccess() throws WebLayerException {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test");
        request.setContent("testContent".getBytes());
        assertEquals("testContent", RequestHandlerUtils.getRequestBodyAsString(new HttpServletRequestWrapper(request)));
    }

    @Test
    public void testException() throws WebLayerException {
        exception.expect(WebLayerException.class);
        exception.expectMessage("Could not successfully read request body.");
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test");
        RequestHandlerUtils.getRequestBodyAsString(new HttpServletRequestWrapper(request));
    }
}
