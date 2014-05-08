package ch.alv.components.iam.mock;

import ch.alv.components.core.search.SearchQueryFactory;
import ch.alv.components.core.search.ValuesProvider;

/**
 * * Mock implementation of the {@link SearchQueryFactory} interface.
 *
 * @since 1.0.0
 */
public class MockSearchQueryFactory implements SearchQueryFactory {
    @Override
    public Object createQuery(String searchName, ValuesProvider valuesProvider, Class<?> targetClass) {
        return null;  // not required
    }
}
