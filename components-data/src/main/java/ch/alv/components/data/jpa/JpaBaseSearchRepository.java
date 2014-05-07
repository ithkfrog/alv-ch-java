package ch.alv.components.data.jpa;

import ch.alv.components.core.utils.ReflectionUtils;
import ch.alv.components.data.internal.BaseSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of the {@link ch.alv.components.data.SearchRepository} interface.
 *
 * @since 1.0.0
 */

public class JpaBaseSearchRepository<TYPE, ID extends Serializable> extends BaseSearchRepository<TYPE, ID> {

    private static final Logger LOG = LoggerFactory.getLogger(JpaBaseSearchRepository.class);

    @PersistenceContext
    protected EntityManager em;


    /* (non-Javadoc)
     * @see ch.alv.components.data.search.BaseSearchRepositoryImpl#fetchFromSource(org.springframework.data.domain.Pageable,
     *                                                                             ch.alv.components.core.search.SearchImpl,
     *                                                                             ch.alv.components.core.search.SearchValuesProvider,
     *                                                                             java.lang.String)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected List<TYPE> fetchFromSource(Pageable pageable, Object search) {
        Class<TYPE> entityClass = ReflectionUtils.determineFirstGenericParam(getClass());
        String queryString;
        TypedQuery<TYPE> typedQuery;

        Object finalSearch = search;
        if (finalSearch == null) {
            finalSearch = "select o from " + entityClass.getSimpleName() + " o";
        }
        LOG.debug("Trying to execute search: " + finalSearch.toString());
        if (finalSearch instanceof String) {
            queryString = (String) finalSearch;
            typedQuery = em.createQuery(queryString, entityClass);
        } else {
            throw new IllegalStateException("Parameter 'search' must be a jpa query string.");
        }
        return typedQuery.getResultList();
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.BaseSearchRepositoryImpl#getAll(org.springframework.data.domain.Pageable)
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page<TYPE> getAll(Pageable pageable) {
        Class<? extends TYPE> entityClass = getEntityClass();
        String queryString = "select o from " + entityClass.getSimpleName() + " o";
        return  createPage(pageable, fetchFromSource(pageable, queryString));
    }

    @Override
    public Page<TYPE> getAll(Iterable<ID> ids) {
        List<TYPE> list = new ArrayList<>();
        for (ID id : ids) {
            TYPE entity = getOne(id);
            if (entity != null) {
                list.add(entity);
            }
        }
        return createPage(null, list);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.BaseSearchRepositoryImpl#getById(java.lang.String)
     */
    public TYPE getOne(ID id) {
        Class<? extends TYPE> entityClass = ReflectionUtils.determineFirstGenericParam(getClass());
        return em.find(entityClass, id);
    }

}
