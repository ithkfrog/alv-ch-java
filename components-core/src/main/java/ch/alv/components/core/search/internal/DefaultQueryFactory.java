package ch.alv.components.core.search.internal;

import ch.alv.components.core.search.NoSuchSearchException;
import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.SearchQueryFactory;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.core.utils.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Spring enabled implementation of the {@link ch.alv.components.core.search.SearchQueryFactory} interface.
 *
 * @since 1.0.0
 */
public class DefaultQueryFactory implements SearchQueryFactory {

    private final ApplicationContextProvider contextProvider;

    public DefaultQueryFactory(ApplicationContextProvider contextProvider) {
        this.contextProvider = contextProvider;
    }


    /**
     * (non-Javadoc)
     * @see SearchQueryFactory#createQuery)
     */
    @Override
    public Object createQuery(String searchName, ValuesProvider valuesProvider, Class<?> targetClass) {
        if (StringHelper.isEmpty(searchName)) {
            throw new IllegalArgumentException("Argument 'searchName' must not be empty.");
        }
        Search search = findSearch(searchName);
        if (search == null) {
            throw new NoSuchSearchException(searchName);
        }
        return search.createQuery(valuesProvider, targetClass);
    }

    /**
     * Find a search implementing bean. If no search with the provided name can be found, a runtimeException is thrown.
     * @param searchName the name of the search which must be provided.
     * @return the matching search bean
     */
    protected Search findSearch(String searchName) {
        Map<String, Search> providers = contextProvider.getBeansOfType(Search.class);
        List<Search> hits = new ArrayList<>();
        for (String beanName : providers.keySet()) {
            Search provider = providers.get(beanName);
            if (provider.getName().equals(searchName)) {
                hits.add(provider);
            }
        }
        if (hits.size() == 0) {
            return null;
        }
        if (hits.size() > 1) {
            throw new IllegalStateException("The name '" + searchName + "' is used for more than 1 search.");
        }
        return hits.get(0);
    }
}
