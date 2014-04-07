package ch.alv.components.persistence.repository;

import ch.alv.components.core.reflection.ReflectionUtils;
import ch.alv.components.core.reflection.ReflectionUtilsException;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.persistence.search.JpaDynamicSearchAdapter;
import ch.alv.components.persistence.search.*;
import ch.alv.components.persistence.search.internal.NoSuchSearchException;
import ch.alv.components.persistence.search.SearchRegistry;
import ch.alv.components.persistence.search.ValuesProvider;
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
import java.util.Map;

/**
 * Base implementation of the {@link CustomRepository} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public abstract class CustomRepositoryImpl<TYPE> implements CustomRepository<TYPE> {

    private static final String DEFAULT_SEARCH = "defaultSearch";

    private static final int DEFAULT_PAGE_SIZE = 100;

    private static final Logger LOG = LoggerFactory.getLogger(CustomRepositoryImpl.class);

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Page<TYPE> find(Pageable pageable, ValuesProvider valuesProvider) {
        return findInternal(pageable, null, valuesProvider);
    }

    public Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider valuesProvider) {
        return findInternal(pageable, searchName, valuesProvider);
    }

    @Override
    public Page<TYPE> find(ValuesProvider valuesProvider) {
        return findInternal(new PageRequest(0, DEFAULT_PAGE_SIZE), null, valuesProvider);
    }

    @Override
    public Page<TYPE> find(String searchName, ValuesProvider valuesProvider) {
        return findInternal(new PageRequest(0, DEFAULT_PAGE_SIZE), searchName, valuesProvider);
    }

    private Page<TYPE> findInternal(Pageable pageable, String searchName, ValuesProvider valuesProvider) {
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

    private TypedQuery<TYPE> createTypedQuery(String searchName, ValuesProvider valuesProvider) throws NoSuchSearchException, ReflectionUtilsException {
        if (StringHelper.isEmpty(searchName)) {
            searchName = DEFAULT_SEARCH;
        }
        Search search = findSearch(searchName);
        Class<TYPE> entityClass = ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(getClass());

        String queryString = "";
        if (search instanceof DynamicSearch) {
            DynamicSearchImpl dynamicSearch = (DynamicSearchImpl) search;
            if (dynamicSearch.getFroms().isEmpty()) {
                dynamicSearch.from("a", entityClass);
            }
            JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter((DynamicSearchImpl) search);
            queryString = adapter.render(valuesProvider);
        } else if (search instanceof HardCodedSearch) {
            HardCodedSearch hardCodedSearch = (HardCodedSearch) search;
            queryString = String.format(hardCodedSearch.getTemplate(), entityClass.getSimpleName());
        }
        LOG.debug(queryString);
        TypedQuery<TYPE> query = em.createQuery(queryString, entityClass);
        if (valuesProvider == null) {
            return query;
        }
        Map<String, Object> values = valuesProvider.getValues();
        for (String key : values.keySet()) {
            query.setParameter(key, values.get(key));
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

    private Search findSearch(String searchName) throws NoSuchSearchException {
        return SearchRegistry.getSearch(searchName);
    }
}
