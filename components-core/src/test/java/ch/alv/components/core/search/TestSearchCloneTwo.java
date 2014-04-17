package ch.alv.components.core.search;

/**
 * Search for test concerns.
 *
 * @since 1.0.0
 */
public class TestSearchCloneTwo implements Search {

    @Override
    public String getName() {
        return "testSearchClones";
    }

    @Override
    public Object createQuery(SearchValuesProvider searchValuesProvider, Class<?> targetClass) {
        return null;
    }
}
