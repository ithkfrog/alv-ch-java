package ch.alv.components.web.api.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.service.data.PagingDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Mock implementation of a {@link PagingDataService}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockPagingDataService implements PagingDataService<String> {

    private List<?> data = new ArrayList<>();

    @Override
    public <T extends Identifiable> T find(String id, Class<T> entityClass) throws ServiceLayerException {
        if ("hasId".equals(id)) {
            Identifiable entity = new MockTargetedResource();
            if (entityClass == MockResource.class) {
                entity = new MockResource();
            }
            entity.setId("99");
            return (T) entity;
        }
        return null;
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Collection<String> id, Class<T> entityClass) throws ServiceLayerException {
        return null;
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Class<T> entityClass) throws ServiceLayerException {
        return null;
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, String queryName, ValuesProvider params, Class<T> entityClass) throws ServiceLayerException {
        List list = new ArrayList();
        if ("defaultQuery".equals(queryName)) {
            if (entityClass == MockResource.class) {
                MockResource resource = new MockResource();
                resource.setId(UUID.randomUUID().toString());
                list.add(resource);
            } else if (entityClass == MockTargetedResource.class) {
                MockTargetedResource resource = new MockTargetedResource();
                resource.setId(UUID.randomUUID().toString());
                list.add(resource);
            }
        } else {
            throw new DataLayerException("Could not find query with name '" + queryName + "'.");
        }
        if (pageable.getPageNumber() > 0) {
            return new PageImpl(new ArrayList(), pageable, list.size());
        }
        return new PageImpl(list, pageable, list.size());
    }

    @Override
    public <T extends Identifiable> T save(T entity, Class<T> entityClass) throws ServiceLayerException {
        if (StringHelper.isEmpty(entity.getId())) {
            entity.setId(UUID.randomUUID().toString());
        }
        return entity;
    }

    @Override
    public <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws ServiceLayerException {
        return null;  // do nothing
    }

    @Override
    public <T extends Identifiable<String>> void delete(String s, Class<T> entityClass) throws ServiceLayerException {
        // do nothing
    }

    @Override
    public <T extends Identifiable<String>> void delete(Collection<String> strings, Class<T> entityClass) throws ServiceLayerException {
        // do nothing
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
