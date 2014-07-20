package ch.alv.components.web.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.service.data.DataService;

import java.util.*;

/**
 * Mock implementation of the {@link DataService} interface
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockDataService implements DataService<String> {

    private MockEntity entity = new MockEntity();

    private List<MockEntity> entities = new ArrayList<>();



    public MockDataService() {
        init();
    }

    private void init() {
        entity.setId("testId");
        entity.setVersion(0);
        entities.add(entity);
    }


    @Override
    public <T extends Identifiable> T find(String id, Class<T> entityClass) throws ServiceLayerException {
        for (MockEntity entity : entities) {
            if (entity.getId().equals(id)) {
                return (T) entity;
            }
        }
        return null;
    }

    @Override
    public <T extends Identifiable> List<T> find(Collection<String> id, Class<T> entityClass) throws ServiceLayerException {
        return (List<T>) entities;
    }

    @Override
    public <T extends Identifiable> List<T> find(Class<T> entityClass) throws ServiceLayerException {
        return (List<T>) entities;
    }

    @Override
    public <T extends Identifiable> List<T> find(String queryName, ValuesProvider params, Class<T> entityClass) throws ServiceLayerException {
        String id = params.getStringValue("id");
        if (StringHelper.isNotEmpty(id)) {
            List<MockEntity> result = new ArrayList<>();
            for (MockEntity item : entities) {
                if (item.getId().equals(id)) {
                    result.add(item);
                }
            }
            return (List<T>) result;
        }
        return (List<T>) entities;
    }

    @Override
    public <T extends Identifiable> T save(T entity, Class<T> entityClass) throws ServiceLayerException {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        int index = entities.indexOf(entity);
        if (index > -1) {
            entities.remove(index);
        }
        entities.add((MockEntity) entity);
        return entity;
    }

    @Override
    public <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws ServiceLayerException {
        List<T> persistedEntities = new ArrayList<>();
        for (T entity : entities) {
            persistedEntities.add(save(entity, entityClass));
        }
        return persistedEntities;
    }

    @Override
    public <T extends Identifiable<String>> void delete(String id, Class<T> entityClass) throws ServiceLayerException {
        Iterator<MockEntity> it = entities.iterator();

        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
            }
        }
    }

    @Override
    public <T extends Identifiable<String>> void delete(Collection<String> ids, Class<T> entityClass) throws ServiceLayerException {
        for (String id : ids) {
            delete(id, entityClass);
        }
    }
}
