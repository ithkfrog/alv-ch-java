package ch.alv.components.web.exception;

/**
 * Used to mark illegal request states.
 *
 * @since 1.0.0
 */
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
