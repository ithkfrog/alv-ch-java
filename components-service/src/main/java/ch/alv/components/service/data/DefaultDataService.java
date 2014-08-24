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
public class DefaultDataService<TYPE extends Identifiable<ID>, ID extends Serializable> implements DataService<TYPE, ID> {

    protected final Repository<TYPE, ID> repository;

    public DefaultDataService(Repository<TYPE, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public TYPE find(ID id, Class<TYPE> entityClass) throws ServiceLayerException {
        try {
            return repository.find(id, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TYPE> find(Collection<ID> ids, Class<TYPE> entityClass) throws ServiceLayerException {
        try {
            return repository.find(ids, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TYPE> find(Class<TYPE> entityClass) throws ServiceLayerException {
        try {
            return repository.find(entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TYPE> find(String queryName, ValuesProvider params, Class<TYPE> entityClass) throws ServiceLayerException {
        try {
            return repository.find(queryName, params, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws ServiceLayerException {
        try {
            return repository.save(entity, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws ServiceLayerException {
        try {
            return repository.save(entities, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void delete(ID id, Class<TYPE> entityClass) throws ServiceLayerException {
        try {
            repository.delete(id, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void delete(Collection<ID> ids, Class<TYPE> entityClass) throws ServiceLayerException {
        try {
            repository.delete(ids, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }
}
