package ch.alv.components.data.mock;

import ch.alv.components.core.beans.ModelItem;

import java.io.Serializable;
import java.util.*;

/**
 * In memory implementation of a data store.
 *
 * @since 1.0.0
 */
public class MockModelItemDataStore<TYPE extends ModelItem, ID extends Serializable> {

    private Map<String, TYPE> entitiesMap = new HashMap<>();

    private List<TYPE> entitiesList = new ArrayList<>();

    private UUIDFactory idFactory = new UUIDFactory();

    public MockModelItemDataStore() {
    }

    public MockModelItemDataStore(Map<String, TYPE> entitiesMap) {
        this.entitiesMap = entitiesMap;
    }

    public TYPE findOne(String id) {
        for (String key : entitiesMap.keySet()) {
            if (key.equals(id)) {
                return entitiesMap.get(id);
            }
        }
        return null;
    }

    public Iterable<TYPE> findAll() {
        return entitiesList;
    }

    @SuppressWarnings("unchecked")
    public <S extends TYPE> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(idFactory.newId());
        }
        entitiesMap.put(entity.getId(), entity);
        refreshEntitiesListFromMap();
        return entity;
    }

    public long count() {
        return entitiesMap.size();
    }

    public void delete(ID id) {
        if (id == null) {
            return;
        }
        entitiesMap.remove(id);
        refreshEntitiesListFromMap();
    }

    private void refreshEntitiesListFromMap() {
        entitiesList.clear();
        for (String key : entitiesMap.keySet()) {
            entitiesList.add(entitiesMap.get(key));
        }
    }

    public Map<String, TYPE> getEntitiesMap() {
        return entitiesMap;
    }

    public void setEntitiesMap(Map<String, TYPE> entitiesMap) {
        this.entitiesMap.clear();
        if (entitiesMap == null) {
            return;
        }
        this.entitiesMap = entitiesMap;
        refreshEntitiesListFromMap();
    }

    public UUIDFactory getIdFactory() {
        return idFactory;
    }

    public void setIdFactory(UUIDFactory idFactory) {
        this.idFactory = idFactory;
    }

    public class UUIDFactory implements IdFactory {

        @Override
        public String newId() {
            return UUID.randomUUID().toString();
        }
    }

    public interface IdFactory {
        Object newId();
    }
}
