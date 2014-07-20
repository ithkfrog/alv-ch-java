package ch.alv.components.web.exception;

import ch.alv.components.core.enums.Language;
import ch.alv.components.data.model.NoSuchTextConstantException;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.NoSuchResourceException;
import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the {@link GlobalExceptionController} class
 *
 * @since 1.0.0
 */
public class GlobalExceptionControllerTest {


    @Test
    public void test() {

        GlobalExceptionController controller = new GlobalExceptionController();

        ResponseEntity<?> response = controller.handleAllExceptions(new UnSupportedMethodException());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertEquals("This HTTP method is currently not supported.", response.getBody());

        response = controller.handleAllExceptions(new NoSuchTextConstantException("test.key", Language.GERMAN));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The requested language is not or at least not fully supported.", response.getBody());

        response = controller.handleAllExceptions(new BadRequestException("testMessage"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The request is in a illegal state: testMessage", response.getBody());

        response = controller.handleAllExceptions(new WebLayerException("testMessage", HttpStatus.BAD_REQUEST));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("testMessage", response.getBody());

        response = controller.handleAllExceptions(new NoSuchResourceException("testMessage"));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("There's no resource assigned to 'testMessage'", response.getBody());

        response = controller.handleAllExceptions(null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(((String) response.getBody()).startsWith("An internal server"));
    }
}
