package ch.alv.components.web.api.provider;

/**
 * Thrown in cases of raml parsing exceptions.
 *
 * @since 1.0.0
 */
public class IllegalRamlException extends RuntimeException {

    private static final long serialVersionUID = -755749514017164213L;

    public IllegalRamlException(String msg) {
        super(msg);
    }
}
