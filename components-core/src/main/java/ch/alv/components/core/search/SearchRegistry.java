package ch.alv.components.core.search;

/**
 * Provides access to all relevant search configurations.
 *
 * @since 1.0.0
 */
public interface SearchRegistry {

    /**
     * Find a search configuration by its name.
     * @param searchName the name of the required search config.
     * @return  the configuration with the given name.
     * @throws ch.alv.components.core.search.NoSuchSearchException if no config with the given name is present.
     */
    SearchImpl getSearch(String searchName) throws NoSuchSearchException;

    /**
     * Fetch the search configuration to be used as the default one.
     * @return the default search configuration
     */
    SearchImpl getDefaultSearch();
}
