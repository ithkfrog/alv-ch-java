package ch.alv.components.web.controller;

/**
 * This exception is thrown if a request needs an unknown valuesprovider.
 *
 * @since 1.0.0
 */
public class NoSuchValuesProviderException extends Exception {
    public NoSuchValuesProviderException(String s) {
        super(s);
    }
}
