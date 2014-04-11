package ch.alv.components.core.search;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Base implementation of the {@link ch.alv.components.core.search.SearchRenderer} interface.
 *
 * @since 1.0.0
 */
public abstract class BaseSearchRendererImpl implements SearchRenderer {

    /**
     * The default search to be used if no custom search is provided.
     */
    private final Search defaultSearch;

    /**
     * Constructor
     * @param defaultSearch search configuration that should be used if no custom configuration is passed to rendering method.
     */
    public BaseSearchRendererImpl(Search defaultSearch) {
        this.defaultSearch = defaultSearch;
    }

    /**
     * Subclasses have to implement the logic how to render a search to a query that corresponds
     * to a certain technology.
     * @param search the search configuration which should be rendered.
     * @param valuesProvider provides the parameter values that should be used.
     * @return a query string that considers a certain technology.
     */
    protected abstract String doRender(Search search, ValuesProvider valuesProvider);

    /**
     * Single point of execution for all public render requests.
     * @param search the search configuration to render.
     * @param valuesProvider the params to consider.
     * @return the query string which has been rendered with the subclass' doRender() implementation.
     */
    protected String renderInternal(Search search, ValuesProvider valuesProvider) {

        Search localSearch = search;
        if (localSearch == null) {
            localSearch = getDefaultSearch();
        }
        ValuesProvider localValuesProvider = valuesProvider;
        if (localValuesProvider == null) {
            localValuesProvider = new NullObjectValuesProvider();
        }
        return doRender(localSearch, localValuesProvider);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.core.search.SearchRenderer#render(ch.alv.components.core.search.ValuesProvider)
     */
    @Override
    public String render(ValuesProvider valuesProvider) {
        return renderInternal(getDefaultSearch(), valuesProvider);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.core.search.SearchRenderer#render(ch.alv.components.core.search.SearchImpl, ch.alv.components.core.search.ValuesProvider)
     */
    @Override
    public String render(Search search, ValuesProvider valuesProvider) {
        return renderInternal(search, valuesProvider);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.core.search.SearchRenderer#render(ch.alv.components.core.search.SearchImpl)
     */
    @Override
    public String render(Search search) {
        return renderInternal(search, new NullObjectValuesProvider());
    }

    /* (non-Javadoc)
     * @see ch.alv.components.core.search.SearchRenderer#getDefaultSearch()
     */
    @Override
    public Search getDefaultSearch() {
        return defaultSearch;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object that) {
        return that != null && (that == this || that.getClass() == getClass() && EqualsBuilder.reflectionEquals(this, that));
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 37, this);
    }
}
