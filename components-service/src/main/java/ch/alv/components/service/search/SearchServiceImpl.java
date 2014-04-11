package ch.alv.components.service.search;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.search.SearchImpl;
import ch.alv.components.core.search.SearchRegistry;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.search.SearchRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;

/**
 * Base implementation of the {@link ch.alv.components.service.search.SearchService} interface
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class SearchServiceImpl<TYPE extends ModelItem> implements SearchService<TYPE> {

    private final SearchRepository repository;

    @Resource
    private SearchRegistry searchRegistry;

    public SearchServiceImpl(SearchRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<TYPE> find(ValuesProvider valuesProvider) {
        return getRepository().findWithDefaultSearch(valuesProvider);
    }

    @Override
    public Page<TYPE> find(String searchName, ValuesProvider valuesProvider) {
        return getRepository().findWithCustomSearch(findSearch(searchName), valuesProvider);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, ValuesProvider valuesProvider) {
        return getRepository().findWithDefaultSearch(pageable, valuesProvider);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider valuesProvider) {
        return getRepository().findWithCustomSearch(pageable, findSearch(searchName), valuesProvider);
    }

    protected SearchImpl findSearch(String searchName) {
        return searchRegistry.getSearch(searchName);
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
        if (that == null) { return false; }
        if (that == this) { return true; }
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
