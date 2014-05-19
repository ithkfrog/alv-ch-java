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
public class MockPagingRepository implements PagingRepository<String> {

    private MockRepository internalRepository = new MockRepository();

    @Override
    public <T extends Identifiable> T save(T entity, Class<T> entityClass) throws DataLayerException {
        return internalRepository.save(entity, entityClass);
    }

    @Override
    public <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws DataLayerException {
        return internalRepository.save(entities, entityClass);
    }

    @Override
    public <T extends Identifiable> T find(String id, Class<T> entityClass) throws DataLayerException {
        return internalRepository.find(id, entityClass);
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, String searchName, ValuesProvider params, Class<T> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, internalRepository.find(searchName, params, entityClass)) ;
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Class<T> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, internalRepository.find(entityClass)) ;
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Collection<String> ids, Class<T> entityClass) throws DataLayerException {
        return RepositoryHelper.createPage(pageable, internalRepository.find(ids, entityClass)) ;
    }

    @Override
    public <T extends Identifiable> void delete(String id, Class<T> entityClass) throws DataLayerException {
        internalRepository.delete(id, entityClass);
    }

    @Override
    public <T extends Identifiable> void delete(Collection<String> ids, Class<T> entityClass) throws DataLayerException {
        internalRepository.delete(ids, entityClass);
    }
}
