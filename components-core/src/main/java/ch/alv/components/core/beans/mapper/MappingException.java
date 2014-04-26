package ch.alv.components.core.beans.mapper;

/**
 * Exception to be used for Mapper Exceptions.
 *
 * @since 1.0.0
 */
public class MappingException extends RuntimeException {

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }

}
