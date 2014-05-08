package ch.alv.components.service.internal;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.SearchRepository;
import ch.alv.components.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Base implementation of the {@link ch.alv.components.service.SearchService} interface
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class DefaultSearchService<TYPE extends ModelItem, ID extends Serializable> implements SearchService<TYPE, ID> {

    private final SearchRepository repository;

    public DefaultSearchService(SearchRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public TYPE findOne(ID id) {
        return (TYPE) getRepository().getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> findAll() {
        return getRepository().getAll((Pageable) null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> findAll(Pageable pageable) {
        return getRepository().getAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> find(ValuesProvider valuesProvider) {
        return getRepository().find(valuesProvider);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> find(String searchName, ValuesProvider valuesProvider) {
        return getRepository().find(valuesProvider, searchName);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> find(Pageable pageable, ValuesProvider valuesProvider) {
        return getRepository().find(valuesProvider, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider valuesProvider) {
        return getRepository().find(valuesProvider, pageable, searchName);
    }

    protected SearchRepository getRepository() {
        return repository;
    }

}
