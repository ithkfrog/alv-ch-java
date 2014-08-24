package ch.alv.components.service.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.repository.PagingRepository;
import ch.alv.components.data.repository.RepositoryHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * Mock implementation of the {@link ch.alv.components.data.repository.Repository} interfce.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockPagingRepository<TYPE extends Identifiable<String>> implements PagingRepository<TYPE, String> {

    private MockRepository<TYPE> internalRepository = new MockRepository();

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException {
        return internalRepository.save(entity, entityClass);
    }

    @Override
    public List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws DataLayerException {
        return internalRepository.save(entities, entityClass);
    }

    @Override
    public TYPE find(String id, Class<TYPE> entityClass) throws DataLayerException {
        return internalRepository.find(id, entityClass);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, internalRepository.find(searchName, params, entityClass)) ;
    }

    @Override
    public Page<TYPE> find(Pageable pageable, Class<TYPE> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, internalRepository.find(entityClass)) ;
    }

    @Override
    public Page<TYPE> find(Pageable pageable, Collection<String> ids, Class<TYPE> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, internalRepository.find(ids, entityClass)) ;
    }

    @Override
    public void delete(String id, Class<TYPE> entityClass) throws DataLayerException {
        internalRepository.delete(id, entityClass);
    }

    @Override
    public void delete(Collection<String> ids, Class<TYPE> entityClass) throws DataLayerException {
        internalRepository.delete(ids, entityClass);
    }
}
