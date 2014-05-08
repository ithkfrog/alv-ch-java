package ch.alv.components.data.internal;

import ch.alv.components.core.search.NoSuchSearchException;
import ch.alv.components.core.search.SearchQueryFactory;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.core.utils.ReflectionUtils;
import ch.alv.components.data.SearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of the {@link ch.alv.components.data.SearchRepository} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseSearchRepository<TYPE, ID extends Serializable> implements SearchRepository<TYPE, ID> {

    protected static final int DEFAULT_PAGE_SIZE = 100;

    private static final Logger LOG = LoggerFactory.getLogger(BaseSearchRepository.class);

    @Resource
    private SearchQueryFactory factory;

    /**
     * Implements the access to the data source.
     *
     * @param pageable the pageable to fulfill.
     * @param search   the search / query object to apply.
     * @return a list of items fetched from the data source.
     */
    protected abstract List<TYPE> fetchFromSource(Pageable pageable, Object search);

    /**
     * Central logic how public method requests are handled.
     *
     * @param pageable       the pageable to fulfill.
     * @param searchName     the name of the search to apply.
     * @param valuesProvider provides the values to apply.
     * @return the result page, matching the requirements of the pageable.
     */
    protected Page<TYPE> findInternal(Pageable pageable, String searchName, ValuesProvider valuesProvider) {
        Object search;
        try {
            search = factory.createQuery(searchName, valuesProvider,
                    ReflectionUtils.determineFirstGenericParam(getClass()));
        } catch (NoSuchSearchException e) {
            LOG.debug(e.getMessage() + " Default search will be used.");
            search = null;
        } catch (java.lang.IllegalArgumentException e) {
            LOG.debug(e.getMessage() + " Default search will be used.");
            search = null;
        }
        List<TYPE> result = fetchFromSource(pageable, search);
        if (result == null) {
            return new PageImpl(new ArrayList(), pageable, 0);
        } else {
            return createPage(pageable, result);
        }
    }

    @Override
    public Page<TYPE> getAll() {
        return getAll((Pageable) null);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.SearchRepository#find(ch.alv.components.core.search.ValuesProvider)
     */
    @Override
    public Page<TYPE> find(ValuesProvider valuesProvider) {
        return findInternal(new PageRequest(0, DEFAULT_PAGE_SIZE), null, valuesProvider);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.SearchRepository#find(ch.alv.components.core.search.ValuesProvider, org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<TYPE> find(ValuesProvider valuesProvider, Pageable pageable) {
        return findInternal(pageable, null, valuesProvider);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.SearchRepository#find(ch.alv.components.core.search.ValuesProvider, java.lang.String)
     */
    @Override
    public Page<TYPE> find(ValuesProvider valuesProvider, String searchName) {
        return findInternal(new PageRequest(0, DEFAULT_PAGE_SIZE), searchName, valuesProvider);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.SearchRepository#find(ch.alv.components.core.search.ValuesProvider, org.springframework.data.domain.Pageable, java.lang.String)
     */
    @Override
    public Page<TYPE> find(ValuesProvider valuesProvider, Pageable pageable, String searchName) {
        return findInternal(pageable, searchName, valuesProvider);
    }

    /**
     * Converts a result to a Page that corresponds to the given pageable.
     *
     * @param pageable the pageable to consider.
     * @param result   the result list of the current query.
     * @return a matching with a sliced (or empty) result list as content.
     */
    protected Page<TYPE> createPage(Pageable pageable, List<TYPE> result) {

        Page<TYPE> page = new PageImpl(result);

        int start = 0;
        int pageNumber = 0;
        int pageSize = DEFAULT_PAGE_SIZE;

        if (pageable != null) {
            pageNumber = pageable.getPageNumber();
            start = pageNumber * pageable.getPageSize();
            pageSize = pageable.getPageSize();
        }

        if (start < result.size()) {
            return applyPageableToResultPage(new PageRequest(pageNumber, pageSize), page);
        } else {
            return new PageImpl(new ArrayList(), new PageRequest(pageNumber, pageSize), result.size());
        }
    }

    /**
     * Creates a list which considers the pageable.
     *
     * @param pageable the pageable requirements.
     * @param result   list of all result items.
     * @return a fresh, pageable-conform list.
     */
    protected List<TYPE> applyPageableToList(Pageable pageable, List<TYPE> result) {
        if (pageable == null) {
            return result;
        }
        int offset = pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int numberOfElements = result.size();

        if (offset > numberOfElements) {
            return new ArrayList();
        }
        if (numberOfElements < offset + pageSize) {
            return result.subList(offset, numberOfElements);
        }
        return result.subList(offset, offset + pageSize);
    }

    /**
     * Creates a page which considers the pageable.
     *
     * @param pageable the pageable requirements.
     * @param result   page of all result items.
     * @return a fresh, pageable-conform page.
     */
    protected Page<TYPE> applyPageableToResultPage(Pageable pageable, Page<TYPE> result) {
        if (pageable == null) {
            return result;
        }
        int offset = pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int numberOfElements = result.getNumberOfElements();

        if (offset > numberOfElements) {
            return new PageImpl(new ArrayList(), pageable, numberOfElements);
        }
        if (numberOfElements < offset + pageSize) {
            return new PageImpl(applyPageableToList(pageable, result.getContent()), pageable, numberOfElements);
        }
        return new PageImpl(applyPageableToList(pageable, result.getContent()), pageable, numberOfElements);
    }

    protected Class<? extends TYPE> getEntityClass() {
        return ReflectionUtils.determineFirstGenericParam(getClass());
    }

}
