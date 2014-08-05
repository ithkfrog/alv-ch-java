package ch.alv.components.data.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.adapter.DataStoreAdapter;

import java.util.*;

/**
 * Mock implementation of the {@link ch.alv.components.data.adapter.DataStoreAdapter} interface.
 *
 * @since 1.0.0
 */
public class MockDataStoreAdapter implements DataStoreAdapter<String> {

    Map<String, MockModelItem> entitiesMap = new HashMap<>();

    List<MockModelItem> entitiesList = new ArrayList<>();



    @Override
    public <T extends Identifiable<String>> T save(T entity, Class<T> entityClass) throws DataLayerException {
        entitiesList.add((MockModelItem) entity);
        fillMap();
        return entity;
    }

    @Override
    public <T extends Identifiable<String>> T find(String id, Class<T> entityClass) throws DataLayerException {
        return (T) entitiesMap.get(id);
    }

    @Override
    public <T extends Identifiable<String>> List<T> find(String queryName, ValuesProvider params, Class<T> entityClass) throws DataLayerException {
        return (List<T>) entitiesList;
    }

    @Override
    public <T extends Identifiable<String>> List<T> find(Class<T> entityClass) throws DataLayerException {
        return (List<T>) entitiesList;
    }

    @Override
    public void delete(String id, Class<?> entityClass) throws DataLayerException {
        Iterator<MockModelItem> it = entitiesList.iterator();
        boolean fillMap = false;
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
                fillMap = true;
            }
        }
        if (fillMap) {
            fillMap();
        }
    }

    private void fillMap() {
        entitiesMap.clear();
        for (MockModelItem entity : entitiesList) {
            entitiesMap.put(entity.getId(), entity);
        }
    }
}
