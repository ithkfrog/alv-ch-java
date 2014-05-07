package ch.alv.components.data.mock;

import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.SearchValuesProvider;

/**
 * Mock implementation of the {@link Search} interface that returns a null.
 *
 * @since 1.0.0
 */
public class TestBaseSearchRepositoryNullSearch implements Search {

    @Override
    public Object createQuery(SearchValuesProvider searchValuesProvider, Class<?> targetClass) {
        return null; // not required
    }

    @Override
    public String getName() {
        return "baseSearchRepositoryNullSearch";
    }
}
