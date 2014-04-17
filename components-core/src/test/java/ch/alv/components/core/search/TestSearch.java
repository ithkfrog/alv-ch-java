package ch.alv.components.core.search;

/**
 * Search implementation for test purposes.
 *
 * @since 1.0.0
 */
public class TestSearch implements Search {

    @Override
    public String getName() {
        return "testSearch";
    }

    @Override
    public Object createQuery(SearchValuesProvider searchValuesProvider, Class<?> targetClass) {
        return "testQuery for " + targetClass.getSimpleName();
    }
}
