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
public class MockDataService<TYPE extends Identifiable<String>> implements DataService<TYPE, String> {

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
    public TYPE find(String id, Class<TYPE> entityClass) throws ServiceLayerException {
        for (MockEntity entity : entities) {
            if (entity.getId().equals(id)) {
                return (TYPE) entity;
            }
        }
        return null;
    }

    @Override
    public List<TYPE> find(Collection<String> id, Class<TYPE> entityClass) throws ServiceLayerException {
        return (List<TYPE>) entities;
    }

    @Override
    public List<TYPE> find(Class<TYPE> entityClass) throws ServiceLayerException {
        return (List<TYPE>) entities;
    }

    @Override
    public List<TYPE> find(String queryName, ValuesProvider params, Class<TYPE> entityClass) throws ServiceLayerException {
        String id = params.getStringValue("id");
        if (StringHelper.isNotEmpty(id)) {
            List<MockEntity> result = new ArrayList<>();
            for (MockEntity item : entities) {
                if (item.getId().equals(id)) {
                    result.add(item);
                }
            }
            return (List<TYPE>) result;
        }
        return (List<TYPE>) entities;
    }

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws ServiceLayerException {
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
    public List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws ServiceLayerException {
        List<TYPE> persistedEntities = new ArrayList<>();
        for (TYPE entity : entities) {
            persistedEntities.add(save(entity, entityClass));
        }
        return persistedEntities;
    }

    @Override
    public void delete(String id, Class<TYPE> entityClass) throws ServiceLayerException {
        Iterator<MockEntity> it = entities.iterator();

        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
            }
        }
    }

    @Override
    public void delete(Collection<String> ids, Class<TYPE> entityClass) throws ServiceLayerException {
        for (String id : ids) {
            delete(id, entityClass);
        }
    }
}
