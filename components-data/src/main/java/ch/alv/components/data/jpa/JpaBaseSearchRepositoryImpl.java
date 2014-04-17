package ch.alv.components.data.jpa;

import ch.alv.components.core.reflection.ReflectionUtils;
import ch.alv.components.data.search.BaseSearchRepositoryImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Base implementation of the {@link ch.alv.components.data.search.SearchRepository} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class JpaBaseSearchRepositoryImpl<TYPE> extends BaseSearchRepositoryImpl<TYPE> {

    private static final Logger LOG = LoggerFactory.getLogger(JpaBaseSearchRepositoryImpl.class);

    @PersistenceContext
    protected EntityManager em;


    /* (non-Javadoc)
     * @see ch.alv.components.data.search.BaseSearchRepositoryImpl#fetchFromSource(org.springframework.data.domain.Pageable,
     *                                                                             ch.alv.components.core.search.SearchImpl,
     *                                                                             ch.alv.components.core.search.SearchValuesProvider,
     *                                                                             java.lang.String)
     */
    @Override
    protected List<TYPE> fetchFromSource(Pageable pageable, Object search) {
        Class<TYPE> entityClass = ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(getClass());
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
        } else if (finalSearch instanceof TypedQuery) {
            typedQuery = (TypedQuery<TYPE>) finalSearch;
        } else if (finalSearch instanceof CriteriaQuery) {
            typedQuery = em.createQuery((CriteriaQuery<TYPE>) finalSearch);
        } else {
            throw new IllegalStateException("Parameter 'search' is not a known jpa query provider");
        }
        return typedQuery.getResultList();
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.BaseSearchRepositoryImpl#getAll(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<TYPE> getAll(Pageable pageable) {
        Class<? extends TYPE> entityClass = getEntityClass();
        String queryString = "select o from " + entityClass.getSimpleName() + " o";
        TypedQuery<TYPE> query = (TypedQuery<TYPE>) em.createQuery(queryString, entityClass);
        List<TYPE> result = query.getResultList();
        return createPage(pageable, result);
    }


    /* (non-Javadoc)
     * @see ch.alv.components.data.search.BaseSearchRepositoryImpl#getById(java.lang.String)
     */
    @Override
    public TYPE getById(String id) {
        Class<? extends TYPE> entityClass = ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(getClass());
        return em.find(entityClass, id);
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
        if (that == this) {
            return true;
        }
        if (that.getClass() != getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 37, this);
    }


}
