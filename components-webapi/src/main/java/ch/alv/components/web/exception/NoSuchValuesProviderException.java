package ch.alv.components.web.exception;

/**
 * This exception is thrown if a request needs an unknown valuesprovider.
 *
 * @since 1.0.0
 */
public class NoSuchValuesProviderException extends Exception {

    private static final long serialVersionUID = -18215080279349725L;

    public NoSuchValuesProviderException(String s) {
        super(s);
    }
}
