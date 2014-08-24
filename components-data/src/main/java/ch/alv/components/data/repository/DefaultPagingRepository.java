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
public class DefaultPagingRepository<TYPE extends Identifiable<ID>, ID extends Serializable> implements PagingRepository<TYPE, ID> {

    private final Repository<TYPE, ID> listRepository;

    public DefaultPagingRepository(DataStoreAdapter adapter) {
        this.listRepository = new DefaultRepository<>(adapter);
    }

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException {
        return listRepository.save(entity, entityClass);
    }

    @Override
    public List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws DataLayerException {
        return listRepository.save(entities, entityClass);
    }

    @Override
    public TYPE find(ID id, Class<TYPE> entityClass) throws DataLayerException {
        return listRepository.find(id, entityClass);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, listRepository.find(searchName, params, entityClass));
    }

    @Override
    public Page<TYPE> find(Pageable pageable, Class<TYPE> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, listRepository.find(entityClass));
    }

    @Override
    public Page<TYPE> find(Pageable pageable, Collection<ID> ids, Class<TYPE> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, listRepository.find(ids, entityClass)) ;
    }

    @Override
    public void delete(ID id, Class<TYPE> entityClass) throws DataLayerException {
        listRepository.delete(id, entityClass);
    }

    @Override
    public void delete(Collection<ID> ids, Class<TYPE> entityClass) throws DataLayerException {
        listRepository.delete(ids, entityClass);
    }

}
