package ch.alv.components.service.persistence;

import ch.alv.components.core.model.ModelItem;
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
public class PersistenceServiceImpl<TYPE extends ModelItem, IDTYPE extends Serializable, REPO extends PagingAndSortingRepository<TYPE, IDTYPE>> implements PersistenceService<TYPE, IDTYPE> {

    private final REPO repo;

    public PersistenceServiceImpl(REPO repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> getAll(Pageable pageable) {
        return getRepository().findAll(pageable);
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
    public Iterable<TYPE> saveAll(List<TYPE> items) {
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
