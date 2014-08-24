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
public class DefaultRepository<TYPE extends Identifiable<ID>, ID extends Serializable> implements Repository<TYPE, ID> {

    private final DataStoreAdapter<TYPE, ID> adapter;

    public DefaultRepository(DataStoreAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    @Transactional
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException {
        try {
            return adapter.save(entity, entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws DataLayerException {
        try {
            List<TYPE> savedEntities = new ArrayList<>();
            for (TYPE entity : entities) {
                savedEntities.add(save(entity, entityClass));
            }
            return savedEntities;
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public TYPE find(ID id, Class<TYPE> entityClass) throws DataLayerException {
        try {
            return adapter.find(id, entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public List<TYPE> find(String searchName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException {
        try {
            return adapter.find(searchName, params, entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public List<TYPE> find(Class<TYPE> entityClass) throws DataLayerException {
        try {
            return adapter.find(entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public List<TYPE> find(Collection<ID> ids, Class<TYPE> entityClass) throws DataLayerException {
        try {
            List<TYPE> foundEntities = new ArrayList<>();
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
    public void delete(ID id, Class<TYPE> entityClass) throws DataLayerException {
        try {
            adapter.delete(id, entityClass);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    @Override
    @Transactional
    public void delete(Collection<ID> ids, Class<TYPE> entityClass) throws DataLayerException {
        try {
            for (ID id : ids) {
                delete(id, entityClass);
            }
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

}
