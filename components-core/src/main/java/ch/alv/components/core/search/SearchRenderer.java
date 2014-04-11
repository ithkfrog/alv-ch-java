package ch.alv.components.core.search;

/**
 * SearchRenderer realize the ability to render an abstract search definition (see {@link SearchImpl}) for  a
 * certain technology (e.g. JPA, JDBC, SolR, ElasticSearch).
 *
 * @since 1.0.0
 */
public interface SearchRenderer {

    /**
     * Renders the default search configuration (see {@link SearchImpl}) with the provided values.
     *
     * @param valuesProvider provides the values which should be used in the query.
     * @return a technology dependent representation of the search.
     */
    String render(ValuesProvider valuesProvider);

    /**
     * Renders the given search configuration (see {@link SearchImpl}) with the provided values.
     *
     * @param search an abstract description of the search.
     * @param valuesProvider provides the values which should be used in the query.
     * @return a technology dependent representation of the search.
     */
    String render(SearchImpl search, ValuesProvider valuesProvider);

    /**
     * Renders the given search configuration (see {@link SearchImpl}) without any params / values.
     *
     * @param search an abstract description of the search.
     * @return a technology dependent representation of the search.
     */
    String render(SearchImpl search);

    /**
     * Provides a search configuration that should be used if no custom configuration is passed to the renderer.
     *
     * @return the default search configuration.
     */
    SearchImpl getDefaultSearch();

    /**
     * Decorates a value with matching wildcards.
     * @param name the name of the attribute for which one the given value will be used.
     * @param value the value to decorate.
     * @return the decorated value.
     */
    Object decorateValue(SearchImpl search, String name, Object value);
}
