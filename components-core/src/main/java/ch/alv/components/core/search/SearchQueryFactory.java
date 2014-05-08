package ch.alv.components.core.search;

/**
 * Factory for search / query objects. The provided query may be technology specific (String, Builder, ...)
 *
 * @since 1.0.0
 */
public interface SearchQueryFactory {

    /**
     * Create a query object dynamically.
     * @param searchName the name of the search that is executed.
     * @param valuesProvider the values provider.
     * @param targetClass the class of which the result will be.
     * @return a platform / store type specific query object.
     */
    Object createQuery(String searchName, ValuesProvider valuesProvider, Class<?> targetClass);

}
