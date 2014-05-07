package ch.alv.components.service.internal;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.data.PersistenceRepository;
import ch.alv.components.service.PersistenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Base implementation of the {@link ch.alv.components.service.PersistenceService} interface
 *
 * @since 1.0.0
 */
public class DefaultPersistenceService<TYPE extends ModelItem<IDTYPE, VERSION>,
                                    IDTYPE extends Serializable,
                                    REPO extends PersistenceRepository<TYPE, IDTYPE, VERSION>,
                                    VERSION extends Serializable>
                                 implements PersistenceService<TYPE, IDTYPE> {

    private final REPO repo;

    public DefaultPersistenceService(REPO repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> findAll() {
        return getRepository().getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TYPE> findAll(Pageable pageable) {
        return getRepository().getAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public TYPE findOne(IDTYPE id) {
        return getRepository().getOne(id);
    }

    @Override
    @Transactional
    public TYPE save(TYPE item) {
        return getRepository().save(item);
    }

    @Override
    @Transactional
    public Page<TYPE> saveAll(List<TYPE> items) {
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
