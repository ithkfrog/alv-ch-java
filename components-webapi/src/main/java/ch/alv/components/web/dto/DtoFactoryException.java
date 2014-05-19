package ch.alv.components.web.dto;

/**
 * Exception thrown when a dto can't be created.
 *
 * @since 1.0.0
 */
public class DtoFactoryException extends RuntimeException {

    private static final long serialVersionUID = 2202615368265974013L;

    public DtoFactoryException(String message) {
        super(message);
    }

    public DtoFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
