package ch.alv.components.core.mapper;

/**
 * Exception to be used for Mapper Exceptions.
 *
 * @since 1.0.0
 */
public class MappingException extends RuntimeException {

    public MappingException() {
    }

    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingException(Throwable cause) {
        super(cause);
    }
}
