package ch.alv.components.web.api.config;

/**
 * Thrown if a request for an unknown resource is handled.
 *
 * @since 1.0.0
 */
public class NoSuchResourceException extends RuntimeException {

    private static final long serialVersionUID = -717512206680486689L;

    public NoSuchResourceException(String message) {
        super(message);
    }
}
