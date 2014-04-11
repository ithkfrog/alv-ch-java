package ch.alv.components.core.search;

import ch.alv.components.core.spring.context.DefaultContextProvider;
import ch.alv.components.core.utils.StringHelper;

/**
 * Default implementation of the {@link ch.alv.components.core.search.SearchRegistry} interface.
 *
 * @since 1.0.0
 */
public class SearchRegistryImpl implements SearchRegistry {

    private static final String DEFAULT_SEARCH_NAME = "defaultSearch";

    /* (non-Javadoc)
     * @see ch.alv.components.service.search.SearchRegistry#getSearch(java.lang.String)
     */
    @Override
    public SearchImpl getSearch(String searchName) throws NoSuchSearchException {
        if (StringHelper.isEmpty(searchName)) {
            throw new IllegalStateException("Parameter 'searchName' must not be null or empty.");
        }
        return DefaultContextProvider.getBeanByName(searchName);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.service.search.SearchRegistry#getDefaultSearch()
     */
    @Override
    public SearchImpl getDefaultSearch() {
        return getSearch(DEFAULT_SEARCH_NAME);
    }
}
