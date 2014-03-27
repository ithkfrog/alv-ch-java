package ch.alv.components.core.reflection;

/**
 * Custom Exception for the ReflectionUtils
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class ReflectionUtilsException extends Exception {

    public ReflectionUtilsException() {
    }

    public ReflectionUtilsException(String message) {
        super(message);
    }

    public ReflectionUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectionUtilsException(Throwable cause) {
        super(cause);
    }

    public ReflectionUtilsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
