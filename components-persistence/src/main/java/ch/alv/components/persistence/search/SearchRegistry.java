package ch.alv.components.persistence.search;

import ch.alv.components.core.spring.context.DefaultContextProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.persistence.search.DynamicSearch;
import ch.alv.components.persistence.search.Search;
import ch.alv.components.persistence.search.internal.NoSuchSearchException;

import java.util.Map;

/**
 * Provides easy access to search definitions.
 *
 * @since 1.0.0
 */
public class SearchRegistry {

    public static Search getSearch(String searchName) {
        if (StringHelper.isEmpty(searchName)) {
            throw new IllegalStateException("Param 'searchName' must not be empty.");
        }
        Map<String, Search> configMap = DefaultContextProvider.getBeansOfType(Search.class);
        if (configMap == null || configMap.isEmpty()) {
            throw new NoSuchSearchException(searchName);
        }
        for (String key : configMap.keySet()) {
            Search search = configMap.get(key);
            if (search.getName().equalsIgnoreCase(searchName)) {
                return search;
            }
        }
        throw new NoSuchSearchException(searchName);
    }

}
