package ch.alv.components.data.query;

import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.core.spring.ApplicationContextProvider;

import java.util.Map;

/**
 * Default implementation of the {@link QueryFactory interface}.
 *
 * @since 1.0.0
 */
public class DefaultQueryFactory implements QueryFactory {

    private final ApplicationContextProvider contextProvider;

    public DefaultQueryFactory(ApplicationContextProvider contextProvider) {
        this.contextProvider = contextProvider;
    }

    @Override
    public <T> T createQuery(String queryName, ValuesProvider params, Map<String, Object> services, Class<?> targetClass) throws NoSuchQueryProviderException {
        return findProvider(queryName).createQuery(params, services, targetClass);
    }

    private QueryProvider findProvider(String queryName) throws NoSuchQueryProviderException {
        Map<String, QueryProvider> queries = contextProvider.getBeansOfType(QueryProvider.class);
        for (String key : queries.keySet()) {
            if (queryName.equalsIgnoreCase(queries.get(key).getName())) {
                return queries.get(key);
            }
        }
        throw new NoSuchQueryProviderException(queryName);
    }
}
