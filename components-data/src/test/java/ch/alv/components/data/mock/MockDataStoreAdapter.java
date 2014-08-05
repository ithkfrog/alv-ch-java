package ch.alv.components.data.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.adapter.DataStoreAdapter;

import java.util.*;

/**
 * Mock implementation of the {@link DataStoreAdapter} interface.
 *
 * @since 1.0.0
 */
public class MockDataStoreAdapter implements DataStoreAdapter<String> {

    Map<String, MockModelItem> entitiesMap = new HashMap<>();

    List<MockModelItem> entitiesList = new ArrayList<>();

    public MockDataStoreAdapter() {
        init();
    }

    private void init() {
        entitiesList.add(new MockModelItem("a", 0, "a"));
        entitiesList.add(new MockModelItem("b", 1, "b"));
        entitiesList.add(new MockModelItem("c", 2, "c"));
        entitiesList.add(new MockModelItem("d", 3, "d"));
        entitiesList.add(new MockModelItem("e", 4, "e"));
        entitiesList.add(new MockModelItem("f", 5, "f"));
        entitiesList.add(new MockModelItem("g", 6, "g"));
        entitiesList.add(new MockModelItem("h", 7, "h"));
        entitiesList.add(new MockModelItem("i", 8, "i"));
        entitiesList.add(new MockModelItem("k", 9, "k"));
        entitiesList.add(new MockModelItem("l", 10, "l"));
        entitiesList.add(new MockModelItem("m", 11, "m"));
        entitiesList.add(new MockModelItem("n", 12, "n"));
        entitiesList.add(new MockModelItem("o", 13, "o"));
        entitiesList.add(new MockModelItem("p", 14, "p"));
        entitiesList.add(new MockModelItem("q", 15, "q"));
        entitiesList.add(new MockModelItem("r", 16, "r"));
        entitiesList.add(new MockModelItem("s", 17, "s"));
        entitiesList.add(new MockModelItem("t", 18, "t"));

        fillMap();
    }

    private void fillMap() {
        entitiesMap.clear();
        for (MockModelItem entity : entitiesList) {
            entitiesMap.put(entity.getId(), entity);
        }
    }

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
}
