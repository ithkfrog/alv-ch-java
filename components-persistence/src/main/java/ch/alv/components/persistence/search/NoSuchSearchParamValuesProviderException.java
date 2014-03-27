package ch.alv.components.persistence.search;

/**
 * Throw this exception if a required SearchParamValuesProvider is not present.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class NoSuchSearchParamValuesProviderException extends Exception {

    public NoSuchSearchParamValuesProviderException(String message) {
        super(message);
    }

}
