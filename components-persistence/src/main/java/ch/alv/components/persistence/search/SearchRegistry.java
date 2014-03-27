package ch.alv.components.persistence.search;

import ch.alv.components.core.spring.context.DefaultContextProvider;

import java.util.Map;

/**
 * Provides easy access to search configurations / definitions.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class SearchRegistry {

    public static Search getSearch(String searchName) {
        Map<String, Search> strategies = DefaultContextProvider.getBeansOfType(Search.class);

        if (strategies.isEmpty()) {
            return null;
        }
        for (String key : strategies.keySet()) {
            if (strategies.get(key).getName().equals(searchName)) {
                return strategies.get(key);
            }
        }
        return null;
    }

}
