package ch.alv.components.web.exception;

import ch.alv.components.data.jpa.NoSuchTextConstantException;
import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

/**
 * Aspect to handle all exceptions
 *
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionController {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception e) {

        if (e instanceof UnSupportedMethodException) {
            return new ResponseEntity<>("This HTTP method is currently not supported.", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if (e instanceof NoSuchTextConstantException) {
            return new ResponseEntity<>("The requested language is not or at least not fully supported.", HttpStatus.BAD_REQUEST);
        }

        if (e instanceof BadRequestException) {
            return new ResponseEntity<>("The request is in a illegal state: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        UUID uuid = UUID.randomUUID();
        LOG.error("Exception id: " + uuid.toString(), e);
        return new ResponseEntity<>("An internal server error occurred. Please inform our support staff with the error id: " + uuid.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
