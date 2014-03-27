package ch.alv.components.web.exception;

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
        UUID uuid = UUID.randomUUID();
        LOG.error("Exception id: " + uuid.toString(), e);
        return new ResponseEntity<>("A internal server error occurred. Please inform our support staff with the error id: " + uuid.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
