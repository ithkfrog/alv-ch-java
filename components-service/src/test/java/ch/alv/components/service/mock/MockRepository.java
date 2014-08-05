package ch.alv.components.service.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.repository.Repository;

import java.util.*;

/**
 * Mock implementation of the {@link Repository} interfce.
 *
 * @since 1.0.0
 */
public class MockRepository implements Repository<String> {


    private final Map<Class, Map> inMemoryData = new HashMap<>();

    public MockRepository() {
        initData();
    }

    private void initData() {
        Map<String, MockModelItem> itemMap = new HashMap<>();
        inMemoryData.put(MockModelItem.class, itemMap);
        for (int i = 0; i < 50; i++) {
            MockModelItem item = new MockModelItem(UUID.randomUUID().toString(), 0, "testItem " + i);
            itemMap.put(item.getId(), item);
        }
    }


    @Override
    public <T extends Identifiable> T save(T entity, Class<T> entityClass) throws DataLayerException {
        Map classMap = new HashMap();
        if (inMemoryData.containsKey(entityClass)) {
            classMap = inMemoryData.get(entityClass);
        }
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        classMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws DataLayerException {
        List<T> persistedEntities = new ArrayList<>();
        for (T entity : entities) {
            persistedEntities.add(save(entity, entityClass));
        }
        return persistedEntities;
    }

    @Override
    public <T extends Identifiable> T find(String id, Class<T> entityClass) throws DataLayerException {
        if (!inMemoryData.containsKey(entityClass)) {
            return null;
        }
        return (T) inMemoryData.get(entityClass).get(id);
    }

    @Override
    public <T extends Identifiable> List<T> find(String searchName, ValuesProvider params, Class<T> entityClass) throws DataLayerException {
        return new ArrayList<>();
    }

    @Override
    public <T extends Identifiable> List<T> find(Class<T> entityClass) throws DataLayerException {
        if (!inMemoryData.containsKey(entityClass)) {
            return new ArrayList<>();
        }
        List<T> entities = new ArrayList<>();
        Map<String, T> map = inMemoryData.get(entityClass);
        for (String key : map.keySet()) {
            entities.add(map.get(key));
        }
        return entities;
    }

    @Override
    public <T extends Identifiable> List<T> find(Collection<String> ids, Class<T> entityClass) throws DataLayerException {
        List<T> entities = new ArrayList<>();
        for (String id : ids) {
            entities.add(find(id, entityClass));
        }
        return entities;
    }

    @Override
    public <T extends Identifiable> void delete(String id, Class<T> entityClass) throws DataLayerException {
        if (!inMemoryData.containsKey(entityClass)) {
            return;
        }
        inMemoryData.get(entityClass).remove(id);
    }

    @Override
    public <T extends Identifiable> void delete(Collection<String> ids, Class<T> entityClass) throws DataLayerException {
        if (!inMemoryData.containsKey(entityClass)) {
            return;
        }
        for (String id : ids) {
            delete(id, entityClass);
        }
    }
}
