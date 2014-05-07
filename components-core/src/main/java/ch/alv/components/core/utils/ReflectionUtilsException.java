package ch.alv.components.core.utils;

/**
 * Custom Exception for the ReflectionUtils
 *
 * @since 1.0.0
 */
public class ReflectionUtilsException extends RuntimeException {

    private static final long serialVersionUID = -2647234294039284758L;

    public ReflectionUtilsException(String message) {
        super(message);
    }
}
