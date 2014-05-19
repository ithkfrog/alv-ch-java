package ch.alv.components.web.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.repository.RepositoryHelper;
import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.service.data.DataService;
import ch.alv.components.service.data.PagingDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * Mock implementation of the {@link ch.alv.components.service.data.DataService} interface
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockPagingDataService implements PagingDataService<String> {

    private DataService<String> internalService = new MockDataService();

    @Override
    public <T extends Identifiable> T find(String id, Class<T> entityClass) throws ServiceLayerException {
        return internalService.find(id, entityClass);
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Collection<String> id, Class<T> entityClass) throws ServiceLayerException {
        return RepositoryHelper.createPage(pageable, internalService.find(id, entityClass));
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Class<T> entityClass) throws ServiceLayerException {
        return RepositoryHelper.createPage(pageable, internalService.find(entityClass));
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, String queryName, ValuesProvider params, Class<T> entityClass) throws ServiceLayerException {
        return RepositoryHelper.createPage(pageable, internalService.find(queryName, params, entityClass));
    }

    @Override
    public <T extends Identifiable> T save(T entity, Class<T> entityClass) throws ServiceLayerException {
        return internalService.save(entity, entityClass);
    }

    @Override
    public <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws ServiceLayerException {
        return internalService.save(entities, entityClass);
    }

    @Override
    public <T extends Identifiable<String>> void delete(String id, Class<T> entityClass) throws ServiceLayerException {
        internalService.delete(id, entityClass);
    }

    @Override
    public <T extends Identifiable<String>> void delete(Collection<String> ids, Class<T> entityClass) throws ServiceLayerException {
        internalService.delete(ids, entityClass);
    }
}
