package ch.alv.components.data.mock;

import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.ValuesProvider;

/**
 * * Mock implementation of the {@link Search} interface.
 *
 * @since 1.0.0
 */
public class TestBaseSearchRepositorySearch implements Search {

    @Override
    public Object createQuery(ValuesProvider valuesProvider, Class<?> targetClass) {
        return null; // not required
    }

    @Override
    public String getName() {
        return "baseSearchRepositorySearch";
    }
}
