package ch.alv.components.data.mock;

import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.ValuesProvider;

import java.util.ArrayList;

/**
 * Mock implementation of the {@link Search} interface that does not return a String query.
 *
 * @since 1.0.0
 */
public class TestBaseSearchRepositoryNonStringSearch implements Search {

    @Override
    public Object createQuery(ValuesProvider valuesProvider, Class<?> targetClass) {
        return new ArrayList<>(); // not required
    }

    @Override
    public String getName() {
        return "baseSearchRepositoryNonStringSearch";
    }
}
