package ch.alv.components.service.persistence;

/**
 * Thrown by the PersistenceService implementations.
 *
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
