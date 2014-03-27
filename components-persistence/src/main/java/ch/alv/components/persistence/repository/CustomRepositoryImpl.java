package ch.alv.components.persistence.repository;

import ch.alv.components.core.reflection.ReflectionUtils;
import ch.alv.components.core.reflection.ReflectionUtilsException;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.persistence.query.SelectQuery;
import ch.alv.components.persistence.query.factory.QueryFactory;
import ch.alv.components.persistence.query.factory.QueryFactoryRegistry;
import ch.alv.components.persistence.query.renderer.JpaQueryRenderer;
import ch.alv.components.persistence.search.NoSuchSearchConfigException;
import ch.alv.components.persistence.search.Search;
import ch.alv.components.persistence.search.SearchRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of the {@link CustomRepository} interface.
 *
 * @since 1.0.0
 */
public abstract class CustomRepositoryImpl<TYPE> implements CustomRepository<TYPE> {

    private static final String DEFAULT_SEARCH = "defaultSearch";

    private static final int DEFAULT_PAGE_SIZE = 100;

    private static final Logger LOG = LoggerFactory.getLogger(CustomRepositoryImpl.class);

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Page<TYPE> find(Pageable pageable, ParamValuesProvider valuesProvider) {
        return findInternal(pageable, null, valuesProvider);
    }

    public Page<TYPE> find(Pageable pageable, String searchName, ParamValuesProvider valuesProvider) {
        return findInternal(pageable, searchName, valuesProvider);
    }

    @Override
    public Page<TYPE> find(ParamValuesProvider valuesProvider) {
        return findInternal(new PageRequest(0, DEFAULT_PAGE_SIZE), null, valuesProvider);
    }

    @Override
    public Page<TYPE> find(String searchName, ParamValuesProvider valuesProvider) {
        return findInternal(new PageRequest(0, DEFAULT_PAGE_SIZE), searchName, valuesProvider);
    }

    private Page<TYPE> findInternal(Pageable pageable, String searchName, ParamValuesProvider valuesProvider) {
        try {
            TypedQuery<TYPE> query = createTypedQuery(searchName, valuesProvider);
            List<TYPE> result = query.getResultList();
            if (result == null || result.size() == 0) {
                return new PageImpl(new ArrayList(), pageable, 0);
            } else {
                return createPageForResult(pageable, result);
            }
        } catch (Exception e) {
            LOG.error("Error while executing search.", e);
            return new PageImpl(new ArrayList(), pageable, 0);
        }
    }

    private TypedQuery<TYPE> createTypedQuery(String searchName, ParamValuesProvider valuesProvider) throws NoSuchSearchConfigException, ReflectionUtilsException {
        if (StringHelper.isEmpty(searchName)) {
            searchName = DEFAULT_SEARCH;
        }
        Search config = findSearchConfig(searchName);
        Class<TYPE> entityClass = ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(getClass());
        QueryFactory factory = QueryFactoryRegistry.getFactory(config.getQueryFactoryName());
        SelectQuery select = factory.createQuery(config, entityClass, valuesProvider);
        String queryString = JpaQueryRenderer.render(select);
        TypedQuery<TYPE> query = em.createQuery(queryString, entityClass);
        for (String key : select.getValues().keySet()) {
            query.setParameter(key, select.getValues().get(key));
        }
        return query;
    }

    private Page<TYPE> createPageForResult(Pageable pageable, List<TYPE> result) {
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

    private Search findSearchConfig(String searchName) throws NoSuchSearchConfigException {
        if (StringHelper.isEmpty(searchName)) {
            throw new NoSuchSearchConfigException("Param 'searchName' must not be empty.");
        }
        Search search = SearchRegistry.getSearch(searchName);

        if (search == null) {
            throw new NoSuchSearchConfigException("No custom search configuration for searchName '" + searchName + "' found.");
        }
        return search;
    }
}
