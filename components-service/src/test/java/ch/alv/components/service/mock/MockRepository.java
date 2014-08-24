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
public class MockRepository<TYPE extends Identifiable<String>> implements Repository<TYPE, String> {


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
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException {
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
    public List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws DataLayerException {
        List<TYPE> persistedEntities = new ArrayList<>();
        for (TYPE entity : entities) {
            persistedEntities.add(save(entity, entityClass));
        }
        return persistedEntities;
    }

    @Override
    public TYPE find(String id, Class<TYPE> entityClass) throws DataLayerException {
        if (!inMemoryData.containsKey(entityClass)) {
            return null;
        }
        return (TYPE) inMemoryData.get(entityClass).get(id);
    }

    @Override
    public List<TYPE> find(String searchName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException {
        return new ArrayList<>();
    }

    @Override
    public List<TYPE> find(Class<TYPE> entityClass) throws DataLayerException {
        if (!inMemoryData.containsKey(entityClass)) {
            return new ArrayList<>();
        }
        List<TYPE> entities = new ArrayList<>();
        Map<String, TYPE> map = inMemoryData.get(entityClass);
        for (String key : map.keySet()) {
            entities.add(map.get(key));
        }
        return entities;
    }

    @Override
    public List<TYPE> find(Collection<String> ids, Class<TYPE> entityClass) throws DataLayerException {
        List<TYPE> entities = new ArrayList<>();
        for (String id : ids) {
            entities.add(find(id, entityClass));
        }
        return entities;
    }

    @Override
    public void delete(String id, Class<TYPE> entityClass) throws DataLayerException {
        if (!inMemoryData.containsKey(entityClass)) {
            return;
        }
        inMemoryData.get(entityClass).remove(id);
    }

    @Override
    public void delete(Collection<String> ids, Class<TYPE> entityClass) throws DataLayerException {
        if (!inMemoryData.containsKey(entityClass)) {
            return;
        }
        for (String id : ids) {
            delete(id, entityClass);
        }
    }
}
