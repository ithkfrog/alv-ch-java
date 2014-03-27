package ch.alv.components.service.persistence;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.persistence.repository.CustomRepository;
import ch.alv.components.persistence.repository.ParamValuesProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Base implementation of the {@link PersistenceService} interface
 *
 * @since 1.0.0
 */
public class PersistenceServiceImpl<TYPE extends ModelItem, IDTYPE extends Serializable, REPO extends PagingAndSortingRepository<TYPE, IDTYPE> & CustomRepository<TYPE>> implements PersistenceService<TYPE, IDTYPE> {

    private final REPO repo;

    protected PersistenceServiceImpl(REPO repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, ParamValuesProvider valuesProvider) {
        return repo.find(pageable, valuesProvider);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, String searchName, ParamValuesProvider valuesProvider) {
        return repo.find(pageable, searchName, valuesProvider);
    }

    @Override
    public Page<TYPE> find(ParamValuesProvider valuesProvider) {
        return repo.find(valuesProvider);
    }

    @Override
    public Page<TYPE> find(String searchName, ParamValuesProvider valuesProvider) {
        return repo.find(searchName, valuesProvider);
    }

    @Override
    @Transactional(readOnly = true)
    public TYPE getById(IDTYPE id) {
        return getRepository().findOne(id);
    }

    @Override
    @Transactional
    public TYPE save(TYPE item) {
        return getRepository().save(item);
    }

    @Override
    @Transactional
    public Iterable<TYPE> save(List<TYPE> items) {
        return getRepository().save(items);
    }

    @Override
    @Transactional
    public void delete(IDTYPE id) {
        getRepository().delete(id);
    }

    protected REPO getRepository() {
        return repo;
    }

}
