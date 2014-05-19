package ch.alv.components.data.repository;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.adapter.DataStoreAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Default implementation of the {@link PagingRepository} interface.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class DefaultPagingRepository<ID extends Serializable> implements PagingRepository<ID> {

    private final Repository<ID> listRepository;

    public DefaultPagingRepository(DataStoreAdapter adapter) {
        this.listRepository = new DefaultRepository<>(adapter);
    }

    @Override
    public <T extends Identifiable> T save(T entity, Class<T> entityClass) throws DataLayerException {
        return listRepository.save(entity, entityClass);
    }

    @Override
    public <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws DataLayerException {
        return listRepository.save(entities, entityClass);
    }

    @Override
    public <T extends Identifiable> T find(ID id, Class<T> entityClass) throws DataLayerException {
        return listRepository.find(id, entityClass);
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, String searchName, ValuesProvider params, Class<T> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, listRepository.find(searchName, params, entityClass));
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Class<T> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, listRepository.find(entityClass));
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Collection<ID> ids, Class<T> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, listRepository.find(ids, entityClass)) ;
    }

    @Override
    public <T extends Identifiable> void delete(ID id, Class<T> entityClass) throws DataLayerException {
        listRepository.delete(id, entityClass);
    }

    @Override
    public <T extends Identifiable> void delete(Collection<ID> ids, Class<T> entityClass) throws DataLayerException {
        listRepository.delete(ids, entityClass);
    }

}
