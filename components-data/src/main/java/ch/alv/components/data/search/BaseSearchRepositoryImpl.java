package ch.alv.components.data.search;

import ch.alv.components.core.reflection.ReflectionUtils;
import ch.alv.components.core.search.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of the {@link ch.alv.components.data.search.SearchRepository} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseSearchRepositoryImpl<TYPE> implements SearchRepository<TYPE> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseSearchRepositoryImpl.class);

    protected static final int DEFAULT_PAGE_SIZE = 100;

    /**
     * Implements the access to the data source.
     * @param pageable the pageable to fulfill.
     * @param search the search configuration to apply.
     * @param valuesProvider provides the values to apply.
     * @param queryString the search as a query string.
     * @return a list of items fetched from the data source.
     */
    protected abstract List<TYPE> fetchFromSource(Pageable pageable, SearchImpl search, ValuesProvider valuesProvider, String queryString);

    /**
     * Provides the renderer to convert a search to a query string.
     * @return the searchToQuery renderer for the implemented technology.
     */
    protected abstract SearchRenderer getRenderer();

    /**
     * Central logic how public method requests are handled.
     * @param pageable the pageable to fulfill.
     * @param search the search configuration to apply.
     * @param valuesProvider provides the values to apply.
     * @return the result page, matching the requirements of the pageable.
     */
    protected Page<TYPE> findInternal(Pageable pageable, SearchImpl search, ValuesProvider valuesProvider) {
        if (search != null && CollectionUtils.isEmpty(search.getSources())) {
            Class<TYPE> entityClass = ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(getClass());
            search.getSources().add(new SearchSource("a", entityClass.getSimpleName()));
        } else {
            Class<TYPE> entityClass = ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(getClass());
            search = new SearchBuilder().find("a", "*").in("a", entityClass.getSimpleName()).build();
        }
        String queryString = renderToQueryString(search, valuesProvider);
        List<TYPE> result = fetchFromSource(pageable, search, valuesProvider, queryString);
        if (result == null || result.size() == 0) {
            return new PageImpl(new ArrayList(), pageable, 0);
        } else {
            return createPage(pageable, result);
        }
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.SearchRepository#findWithDefaultSearch(ch.alv.components.core.search.ValuesProvider)
     */
    @Override
    public Page<TYPE> findWithDefaultSearch(ValuesProvider valuesProvider) {
        return findInternal(new PageRequest(0, DEFAULT_PAGE_SIZE), null, valuesProvider);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.SearchRepository#findWithDefaultSearch(import org.springframework.data.domain.Pageable, ch.alv.components.core.search.ValuesProvider)
     */
    @Override
    public Page<TYPE> findWithDefaultSearch(Pageable pageable, ValuesProvider valuesProvider) {
        return findInternal(pageable, null, valuesProvider);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.SearchRepository#findWithCustomSearch(ch.alv.components.core.search.SearchImpl, ch.alv.components.core.search.ValuesProvider)
     */
    @Override
    public Page<TYPE> findWithCustomSearch(SearchImpl search, ValuesProvider valuesProvider) {
        return findInternal(new PageRequest(0, DEFAULT_PAGE_SIZE), search, valuesProvider);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.SearchRepository#findWithCustomSearch(import org.springframework.data.domain.Pageable, ch.alv.components.core.search.SearchImpl, ch.alv.components.core.search.ValuesProvider)
     */
    @Override
    public Page<TYPE> findWithCustomSearch(Pageable pageable, SearchImpl search, ValuesProvider valuesProvider) {
        return findInternal(pageable, search, valuesProvider);
    }

    /**
     * Uses the SearchRenderer to render the search to a query string.
     *
     * @param search         the search to render.
     * @param valuesProvider the params to use.
     * @return a query string.
     */
    protected String renderToQueryString(SearchImpl search, ValuesProvider valuesProvider) {
        String queryString = getRenderer().render(search, valuesProvider);
        LOG.debug("The " + getRenderer().getClass().getSimpleName() + " provided the query: '" + queryString + "'");
        return queryString;
    }

    /**
     * Converts a result to a Page that corresponds to the given pageable.
     *
     * @param pageable the pageable to consider.
     * @param result   the result list of the current query.
     * @return a matching with a sliced (or empty) result list as content.
     */
    protected Page<TYPE> createPage(Pageable pageable, List<TYPE> result) {
        int start = pageable.getPageNumber() * pageable.getPageSize();
        int end = (pageable.getPageNumber() + 1) * pageable.getPageSize() - 1;

        boolean addContent = start < result.size();
        if (end > result.size()) {
            end = result.size();
        }
        if (addContent) {
            return new PageImpl(result.subList(start, end), pageable, result.size());
        } else {
            return new PageImpl(new ArrayList(), pageable, result.size());
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        return that == this || that.getClass() == getClass() && EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 37, this);
    }

}
