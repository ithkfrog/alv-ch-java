package ch.alv.components.data.repository;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.adapter.DataStoreAdapter;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Default implementation of the {@link Repository} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class DefaultRepository<ID extends Serializable> implements Repository<ID> {

    private final DataStoreAdapter adapter;

    public DefaultRepository(DataStoreAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    @Transactional
    public <T extends Identifiable> T save(T entity, Class<T> entityClass) throws DataLayerException {
        try {
            return (T) adapter.save(entity, entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws DataLayerException {
        try {
            List<T> savedEntities = new ArrayList<>();
            for (T entity : entities) {
                savedEntities.add(save(entity, entityClass));
            }
            return savedEntities;
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public <T extends Identifiable> T find(ID id, Class<T> entityClass) throws DataLayerException {
        try {
            return (T) adapter.find(id, entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public <T extends Identifiable> List<T> find(String searchName, ValuesProvider params, Class<T> entityClass) throws DataLayerException {
        try {
            return adapter.find(searchName, params, entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public <T extends Identifiable> List<T> find(Class<T> entityClass) throws DataLayerException {
        try {
            return adapter.find(entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public <T extends Identifiable> List<T> find(Collection<ID> ids, Class<T> entityClass) throws DataLayerException {
        try {
            List<T> foundEntities = new ArrayList<>();
            for (ID id : ids) {
                foundEntities.add(find(id, entityClass));
            }
            return foundEntities;
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public <T extends Identifiable> void delete(ID id, Class<T> entityClass) throws DataLayerException {
        try {
            adapter.delete(id, entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public <T extends Identifiable> void delete(Collection<ID> ids, Class<T> entityClass) throws DataLayerException {
        try {
            for (ID id : ids) {
                delete(id, entityClass);
            }
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

}
