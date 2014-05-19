package ch.alv.components.service.data;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.repository.PagingRepository;
import ch.alv.components.service.ServiceLayerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Default implementation of the {@link PagingDataService} interface.
 *
 * @since 1.0.0
 */
@Transactional
public class DefaultPagingDataService<ID extends Serializable> implements PagingDataService<ID> {

    private final PagingRepository<ID> repository;

    public DefaultPagingDataService(PagingRepository<ID> repository) {
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
    public <T extends Identifiable> Page<T> find(Pageable pageable, Collection<ID> ids, Class<T> entityClass) throws ServiceLayerException {
        try {
            return repository.find(pageable, ids, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends Identifiable> Page<T> find(Pageable pageable, Class<T> entityClass) throws ServiceLayerException {
        try {
            return repository.find(pageable, entityClass);
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends Identifiable> Page<T> find(Pageable pageable, String queryName, ValuesProvider params, Class<T> entityClass) throws ServiceLayerException {
        try {
            return repository.find(pageable, queryName, params, entityClass);
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
