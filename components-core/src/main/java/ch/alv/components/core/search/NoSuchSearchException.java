package ch.alv.components.core.search;

/**
 * Thrown if no matching search bean exists.
 *
 * @since 1.0.0
 */
public class NoSuchSearchException extends RuntimeException {
    public NoSuchSearchException(String searchName) {
        super("Could not find a search with name '" + searchName + "'.");
    }
}
