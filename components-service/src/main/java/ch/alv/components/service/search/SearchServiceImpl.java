package ch.alv.components.service.search;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.search.SearchValuesProvider;
import ch.alv.components.data.search.SearchRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base implementation of the {@link ch.alv.components.service.search.SearchService} interface
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")

public class SearchServiceImpl<TYPE extends ModelItem> implements SearchService<TYPE> {

    private final SearchRepository repository;

    public SearchServiceImpl(SearchRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> getAll(Pageable pageable) {
        return getRepository().getAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public TYPE getById(String id) {
        return (TYPE) getRepository().getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> find(SearchValuesProvider searchValuesProvider) {
        return getRepository().findWithDefaultSearch(searchValuesProvider);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> find(String searchName, SearchValuesProvider searchValuesProvider) {
        return getRepository().findWithCustomSearch(searchName, searchValuesProvider);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> find(Pageable pageable, SearchValuesProvider searchValuesProvider) {
        return getRepository().findWithDefaultSearch(pageable, searchValuesProvider);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> find(Pageable pageable, String searchName, SearchValuesProvider searchValuesProvider) {
        return getRepository().findWithCustomSearch(pageable, searchName, searchValuesProvider);
    }

    protected SearchRepository getRepository() {
        return repository;
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
