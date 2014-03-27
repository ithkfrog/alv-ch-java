package ch.alv.components.persistence.search;

/**
 * Throw this exception if a non-existing custom search is requested.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class NoSuchSearchConfigException extends Exception {

    public NoSuchSearchConfigException(String message) {
        super(message);
    }

}
