package ch.alv.components.data.internal;

import ch.alv.components.core.search.NoSuchSearchException;
import ch.alv.components.core.search.SearchQueryFactory;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataStoreSearchAdapter;
import ch.alv.components.data.SearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
public class DefaultSearchRepository<TYPE, ID extends Serializable> implements SearchRepository<TYPE, ID> {

    protected static final int DEFAULT_PAGE_SIZE = 100;

    private static final Logger LOG = LoggerFactory.getLogger(DefaultSearchRepository.class);

    @Resource
    private SearchQueryFactory factory;

    private final DataStoreSearchAdapter<TYPE, ID> storeAdapter;

    private final Class<? extends TYPE> entityClass;

    protected DefaultSearchRepository(Class<TYPE> entityClass, DataStoreSearchAdapter<TYPE, ID> storeAdapter) {
        this.entityClass = entityClass;
        this.storeAdapter = storeAdapter;
    }

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
            search = factory.createQuery(searchName, valuesProvider, entityClass);
        } catch (NoSuchSearchException e) {
            LOG.debug(e.getMessage() + " Default search will be used.");
            search = null;
        } catch (java.lang.IllegalArgumentException e) {
            LOG.debug(e.getMessage() + " Default search will be used.");
            search = null;
        }
        List<TYPE> result = storeAdapter.fetchFromSource(pageable, search);
        return RepositoryHelper.createPage(pageable, result);
    }

    @Override
    public Page<TYPE> getAll() {
        return getAll((Pageable) null);
    }

    @Override
    public Page<TYPE> getAll(Pageable pageable) {
        return RepositoryHelper.createPage(pageable, storeAdapter.fetchFromSource(pageable, null));
    }

    @Override
    public Page<TYPE> getAll(Iterable<ID> ids) {
        List<TYPE> entities = new ArrayList<>();
        for (ID id : ids) {
            TYPE entity = getOne(id);
            if (entity == null) {
                continue;
            }
            entities.add(entity);
        }
        return RepositoryHelper.createPage(null, entities);
    }

    @Override
    public TYPE getOne(ID id) {
        return storeAdapter.fetchFromSource(id);
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

}
