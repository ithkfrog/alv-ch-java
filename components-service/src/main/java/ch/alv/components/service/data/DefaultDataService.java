package ch.alv.components.service.data;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.repository.Repository;
import ch.alv.components.service.ServiceLayerException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Default implementation of the {@link DataService} interface.
 *
 * @since 1.0.0
 */
@Transactional
public class DefaultDataService<ID extends Serializable> implements DataService<ID> {

    protected final Repository<ID> repository;

    public DefaultDataService(Repository<ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends Identifiable> T find(ID id, Class<T> entityClass) throws ServiceLayerException {
        try {
            return repository.find(id, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends Identifiable> List<T> find(Collection<ID> ids, Class<T> entityClass) throws ServiceLayerException {
        try {
            return repository.find(ids, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends Identifiable> List<T> find(Class<T> entityClass) throws ServiceLayerException {
        try {
            return repository.find(entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends Identifiable> List<T> find(String queryName, ValuesProvider params, Class<T> entityClass) throws ServiceLayerException {
        try {
            return repository.find(queryName, params, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public <T extends Identifiable> T save(T entity, Class<T> entityClass) throws ServiceLayerException {
        try {
            return repository.save(entity, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws ServiceLayerException {
        try {
            return repository.save(entities, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public <T extends Identifiable<ID>> void delete(ID id, Class<T> entityClass) throws ServiceLayerException {
        try {
            repository.delete(id, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public <T extends Identifiable<ID>> void delete(Collection<ID> ids, Class<T> entityClass) throws ServiceLayerException {
        try {
            repository.delete(ids, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }
}
