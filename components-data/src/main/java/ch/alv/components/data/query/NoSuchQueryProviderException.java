package ch.alv.components.data.query;

/**
 * Thrown if no provider for a certain query can be found.
 *
 * @since 1.0.0
 */
public class NoSuchQueryProviderException extends Exception {

    private static final long serialVersionUID = 3360388507212509838L;

    public NoSuchQueryProviderException(String queryName) {
        super("Could not find a QueryProvider with name '" + queryName + "'. Execution aborted.");
    }
}
