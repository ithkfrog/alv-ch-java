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
public class MockDataStoreAdapter<TYPE extends Identifiable<String>> implements DataStoreAdapter<TYPE, String> {

    Map<String, MockModelItem> entitiesMap = new HashMap<>();

    List<MockModelItem> entitiesList = new ArrayList<>();

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException {
        entitiesList.add((MockModelItem) entity);
        fillMap();
        return entity;
    }

    @Override
    public TYPE find(String id, Class<TYPE> entityClass) throws DataLayerException {
        return (TYPE) entitiesMap.get(id);
    }

    @Override
    public List<TYPE> find(String queryName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException {
        return (List<TYPE>) entitiesList;
    }

    @Override
    public List<TYPE> find(Class<TYPE> entityClass) throws DataLayerException {
        return (List<TYPE>) entitiesList;
    }

    @Override
    public void delete(String id, Class<TYPE> entityClass) throws DataLayerException {
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
