package ch.alv.components.service.mock;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.data.PersistenceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of a PersistenceRepository
 *
 * @since 1.0.0
 */
public class MockPersistenceRepository<T extends ModelItem<ID, VERSION>, ID extends Serializable, VERSION extends Serializable>
        implements PersistenceRepository<T, ID, VERSION> {

    @Resource
    private MockModelItemDataStore<T, ID, VERSION> dataStore;

    @Override
    public T getOne(ID id) {
        return dataStore.findOne(id);
    }

    @Override
    public Page<T> getAll() {
        List<T> result = (List<T>) dataStore.findAll();
        return new PageImpl<>(result, new PageRequest(0, 100), result.size());
    }

    @Override
    public Page<T> getAll(Pageable pageable) {
        List<T> result = (List<T>) dataStore.findAll();
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<T> getAll(Iterable<ID> ids) {
        List<T> list = new ArrayList<>();
        for (ID id : ids) {
            list.add(getOne(id));
        }
        return new PageImpl<>(list, new PageRequest(0, list.size()), list.size());
    }

    @Override
    public <S extends T> S save(S entity) {
        return dataStore.save(entity);
    }

    @Override
    public <S extends T> Page<S> save(Iterable<S> entities) {
        List<S> list = new ArrayList<>();
        for (S entity : entities) {
            list.add(dataStore.save(entity));
        }
        return new PageImpl<>(list, new PageRequest(0, 100), list.size());
    }

    @Override
    public boolean exists(ID id) {
        T entity = dataStore.findOne(id);
        return entity != null;
    }

    @Override
    public long count() {
        return dataStore.count();
    }

    @Override
    public void delete(ID id) {
        dataStore.delete(id);
    }

    @Override
    public void delete(T entity) {
        delete(entity.getId());
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        for (T entity : entities) {
            delete(entity.getId());
        }
    }

    @Override
    public void deleteAll() {
        dataStore.setEntitiesMap(null);
    }
}
