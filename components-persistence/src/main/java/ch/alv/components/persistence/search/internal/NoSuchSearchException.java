package ch.alv.components.persistence.search.internal;

/**
 * This exception will be thrown if no matching search be provided.
 *
 * @since 1.0.0
 */
public class NoSuchSearchException extends RuntimeException {
    public NoSuchSearchException(String searchName) {
        super("No search with name '" + searchName + "' found.");
    }
}
