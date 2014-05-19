package ch.alv.components.service;

/**
 * Generic exception for service layer error handling.
 *
 * @since 1.0.0
 */
public class RuntimeServiceLayerException extends RuntimeException {

    private static final long serialVersionUID = 3413604819520366274L;

    public RuntimeServiceLayerException(String message) {
        super(message);
    }

    public RuntimeServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeServiceLayerException(Throwable cause) {
        super(cause);
    }
}
