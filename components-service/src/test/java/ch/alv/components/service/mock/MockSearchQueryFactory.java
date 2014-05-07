package ch.alv.components.service.mock;

import ch.alv.components.core.search.SearchQueryFactory;
import ch.alv.components.core.search.SearchValuesProvider;

/**
 * Mock implementation of the {@link SearchQueryFactory} interface.
 *
 * @since 1.0.0
 */
public class MockSearchQueryFactory implements SearchQueryFactory {
    @Override
    public Object createQuery(String searchName, SearchValuesProvider searchValuesProvider, Class<?> targetClass) {
        return null;  // not required
    }
}
