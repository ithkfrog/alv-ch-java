package ch.alv.components.core.search;

/**
 * Provider of a named and store type specific query object.
 *
 * @since 1.0.0
 */
public interface Search {

    /**
     * Create a store type specific query. That may be a String or a criteria-like object.
     * @param valuesProvider the valuesProvider.
     * @param targetClass the type for which the query is created.
     * @return a store type conform query object.
     */
    Object createQuery(ValuesProvider valuesProvider, Class<?> targetClass);

    /**
     * Search objects must carry a unique name.
     * @return the name.
     */
    String getName();

}
