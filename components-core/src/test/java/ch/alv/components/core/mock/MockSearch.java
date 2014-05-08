package ch.alv.components.core.mock;

import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.ValuesProvider;

/**
 * Search implementation for test purposes.
 *
 * @since 1.0.0
 */
public class MockSearch implements Search {

    private String name;

    public MockSearch(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object createQuery(ValuesProvider valuesProvider, Class<?> targetClass) {
        return "testQuery for " + targetClass.getSimpleName();
    }
}
