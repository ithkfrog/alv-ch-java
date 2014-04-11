package ch.alv.components.data.jpa;

import ch.alv.components.core.reflection.ReflectionUtils;
import ch.alv.components.core.search.SearchImpl;
import ch.alv.components.core.search.SearchRenderer;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.search.BaseSearchRepositoryImpl;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

/**
 * Base implementation of the {@link ch.alv.components.data.search.SearchRepository} interface.
 *
 * @since 1.0.0
 */
public class JpaBaseSearchRepositoryImpl<TYPE> extends BaseSearchRepositoryImpl<TYPE> {

    private static final Logger LOG = LoggerFactory.getLogger(JpaBaseSearchRepositoryImpl.class);

    @PersistenceContext
    protected EntityManager em;

    @Resource
    private JpaSearchToQueryRenderer renderer;

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.BaseSearchRepositoryImpl#fetchFromSource(org.springframework.data.domain.Pageable,
     *                                                                             ch.alv.components.core.search.SearchImpl,
     *                                                                             ch.alv.components.core.search.ValuesProvider,
     *                                                                             java.lang.String)
     */
    protected List<TYPE> fetchFromSource(Pageable pageable, SearchImpl search, ValuesProvider valuesProvider, String queryString) {
        Class<TYPE> entityClass = ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(getClass());
        TypedQuery<TYPE> query;
        query = em.createQuery(queryString, entityClass);
        if (valuesProvider != null) {
            Map<String, Object> values = valuesProvider.getValues();
            for (String key : values.keySet()) {
                try {
                    query.setParameter(key, renderer.decorateValue(search, key, values.get(key)));
                } catch (IllegalArgumentException e) {
                    LOG.info("Error while trying to set a parameter value.");
                }
            }
        }
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see ch.alv.components.data.search.BaseSearchRepositoryImpl#getRenderer()
     */
    @Override
    protected SearchRenderer getRenderer() {
        return renderer;
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
