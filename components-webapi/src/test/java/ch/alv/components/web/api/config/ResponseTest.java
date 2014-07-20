package ch.alv.components.web.api.config;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.ResourceConfiguration} class.
 *
 * @since 1.0.0
 */
public class ResponseTest {

    private Response response = new Response();

    @Test
    public void testHeaders() {
        List<HeaderParameter> headers = new ArrayList<>();
        response.setHeaders(headers);
        assertEquals(headers, response.getHeaders());
    }

    @Test
    public void testDescription() {
        response.setDescription("testDescription");
        assertEquals("testDescription", response.getDescription());
    }

    @Test
    public void testBody() {
        List<MimeType> body = new ArrayList<>();
        assertFalse(response.hasBody());
        response.setBody(body);
        assertFalse(response.hasBody());
        body.add(new MimeType());
        response.setBody(body);
        assertEquals(body, response.getBody());
        assertTrue(response.hasBody());
    }

}
