package ch.alv.components.web;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

/**
 * Test for the {@link ch.alv.components.core.file.flat.reader.internal.FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
public class WebLayerExceptionTest {

    private static final String MSG = "testMessage";
    private static final HttpStatus STATUS = HttpStatus.BAD_GATEWAY;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testWithStringParam() {
        try {
            throw new WebLayerException(MSG, STATUS);
        } catch (WebLayerException e) {
            assertEquals(MSG, e.getMessage());
            assertEquals(STATUS, e.getResponseStatus());
            assertEquals(MSG, e.getBody());
        }
    }

    @Test
    public void testWithThrowableParam() throws WebLayerException {
        try {
            throw new WebLayerException(new IllegalStateException(MSG), STATUS);
        } catch (WebLayerException e) {
            assertEquals("java.lang.IllegalStateException: testMessage", e.getMessage());
            assertEquals(STATUS, e.getResponseStatus());
            assertEquals(null, e.getBody());
        }
    }

    @Test
    public void testWithStringAndThrowableParams() throws WebLayerException {
        try {
            throw new WebLayerException(MSG, new IllegalStateException(MSG), STATUS);
        } catch (WebLayerException e) {
            assertEquals(MSG, e.getMessage());
            assertEquals(STATUS, e.getResponseStatus());
            assertEquals(MSG, e.getBody());
        }
    }

}
