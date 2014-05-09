package ch.alv.components.data.jpa;

import ch.alv.components.data.DataStoreSearchAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * JPA implementation of the {@link ch.alv.components.data.DataStoreSearchAdapter} interface.
 *
 * @since 1.0.0
 */
public class JpaSearchAdapter<TYPE, ID extends Serializable> implements DataStoreSearchAdapter<TYPE, ID> {

    private static final Logger LOG = LoggerFactory.getLogger(JpaSearchAdapter.class);

    private final Class<? extends TYPE> managedClass;

    protected EntityManager em;

    public JpaSearchAdapter(Class<? extends TYPE> managedClass, EntityManager em) {
        this.managedClass = managedClass;
        this.em = em;
    }

    /* (non-Javadoc)
     * @see DataStoreSearchAdapter#fetchFromSource(org.springframework.data.domain.Pageable, java.lang.Object)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<TYPE> fetchFromSource(Pageable pageable, Object search) {
        String queryString;
        TypedQuery<TYPE> typedQuery;

        Object finalSearch = search;
        if (finalSearch == null) {
            finalSearch = "select o from " + managedClass.getSimpleName() + " o";
        }
        LOG.debug("Trying to execute search: " + finalSearch.toString());
        if (finalSearch instanceof String) {
            queryString = (String) finalSearch;
            typedQuery = (TypedQuery<TYPE>) em.createQuery(queryString, managedClass);
        } else {
            throw new IllegalStateException("Parameter 'search' must be a jpa query string.");
        }
        return typedQuery.getResultList();
    }

    /* (non-Javadoc)
     * @see DataStoreSearchAdapter#fetchFromSource(ID)
     */
    public TYPE fetchFromSource(ID id) {
        return em.find(managedClass, id);
    }

}
