package ch.alv.components.web.exception;

/**
 * Used to mark illegal request states.
 *
 * @since 1.0.0
 */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -8170302725626478197L;

    public BadRequestException(String message) {
        super(message);
    }
}
