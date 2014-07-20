package ch.alv.components.data;

/**
 * Generic exception for data layer error handling.
 *
 * @since 1.0.0
 */
public class DataLayerException extends RuntimeException {

    private static final long serialVersionUID = -866742630189171135L;

    public DataLayerException(String message) {
        super(message);
    }

    public DataLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataLayerException(Throwable cause) {
        super(cause);
    }
}
