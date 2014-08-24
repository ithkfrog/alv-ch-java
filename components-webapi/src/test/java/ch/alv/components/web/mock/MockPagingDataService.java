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
public class MockPagingDataService<TYPE extends Identifiable<String>> implements PagingDataService<TYPE, String> {

    private DataService<TYPE, String> internalService = new MockDataService();

    @Override
    public TYPE find(String id, Class<TYPE> entityClass) throws ServiceLayerException {
        return internalService.find(id, entityClass);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, Collection<String> id, Class<TYPE> entityClass) throws ServiceLayerException {
        return RepositoryHelper.createPage(pageable, internalService.find(id, entityClass));
    }

    @Override
    public Page<TYPE> find(Pageable pageable, Class<TYPE> entityClass) throws ServiceLayerException {
        return RepositoryHelper.createPage(pageable, internalService.find(entityClass));
    }

    @Override
    public Page<TYPE> find(Pageable pageable, String queryName, ValuesProvider params, Class<TYPE> entityClass) throws ServiceLayerException {
        return RepositoryHelper.createPage(pageable, internalService.find(queryName, params, entityClass));
    }

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws ServiceLayerException {
        return internalService.save(entity, entityClass);
    }

    @Override
    public List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws ServiceLayerException {
        return internalService.save(entities, entityClass);
    }

    @Override
    public void delete(String id, Class<TYPE> entityClass) throws ServiceLayerException {
        internalService.delete(id, entityClass);
    }

    @Override
    public void delete(Collection<String> ids, Class<TYPE> entityClass) throws ServiceLayerException {
        internalService.delete(ids, entityClass);
    }
}
