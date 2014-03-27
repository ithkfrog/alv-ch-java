package ch.alv.components.persistence.persistence;

/**
 * Thrown by the PersistenceService implementations.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class PersistenceServiceException extends Exception {

    public PersistenceServiceException() {
    }

    public PersistenceServiceException(String message) {
        super(message);
    }

    public PersistenceServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceServiceException(Throwable cause) {
        super(cause);
    }
}
