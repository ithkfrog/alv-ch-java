package ch.alv.components.service;

/**
 * Generic exception for service layer error handling.
 *
 * @since 1.0.0
 */
public class ServiceLayerException extends Exception {

    private static final long serialVersionUID = 4887233169557173485L;

    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceLayerException(Throwable cause) {
        super(cause);
    }
}
