package ch.alv.components.service.mock;

import ch.alv.components.core.beans.ModelItem;

import java.io.Serializable;
import java.util.*;

/**
 * In memory implementation of a data store, used in the mock implementation {@link MockPersistenceRepository}.
 *
 * @since 1.0.0
 */
public class MockModelItemDataStore<TYPE extends ModelItem<ID, VERSION>, ID extends Serializable, VERSION extends Serializable> {

    private Map<ID, TYPE> entitiesMap = new HashMap<>();

    private List<TYPE> entitiesList = new ArrayList<>();

    private IdFactory idFactory = new UUIDFactory();

    public MockModelItemDataStore() {
        init();
    }

    public MockModelItemDataStore(Map<ID, TYPE> entitiesMap) {
        this.entitiesMap = entitiesMap;
    }

    @SuppressWarnings("unchecked")
    private void init() {
        entitiesList.add((TYPE) new MockModelItem<>("a", 0, "a"));
        entitiesList.add((TYPE) new MockModelItem<>("b", 1, "b"));
        entitiesList.add((TYPE) new MockModelItem<>("c", 2, "c"));
        entitiesList.add((TYPE) new MockModelItem<>("d", 3, "d"));
        entitiesList.add((TYPE) new MockModelItem<>("e", 4, "e"));
        entitiesList.add((TYPE) new MockModelItem<>("f", 5, "f"));
        entitiesList.add((TYPE) new MockModelItem<>("g", 6, "g"));
        entitiesList.add((TYPE) new MockModelItem<>("h", 7, "h"));
        entitiesList.add((TYPE) new MockModelItem<>("i", 8, "i"));
        entitiesList.add((TYPE) new MockModelItem<>("k", 9, "k"));
        entitiesList.add((TYPE) new MockModelItem<>("l", 10, "l"));
        entitiesList.add((TYPE) new MockModelItem<>("m", 11, "m"));
        entitiesList.add((TYPE) new MockModelItem<>("n", 12, "n"));
        entitiesList.add((TYPE) new MockModelItem<>("o", 13, "o"));
        entitiesList.add((TYPE) new MockModelItem<>("p", 14, "p"));
        entitiesList.add((TYPE) new MockModelItem<>("q", 15, "q"));
        entitiesList.add((TYPE) new MockModelItem<>("r", 16, "r"));
        entitiesList.add((TYPE) new MockModelItem<>("s", 17, "s"));
        entitiesList.add((TYPE) new MockModelItem<>("t", 18, "t"));

        for (TYPE entity : entitiesList) {
            entitiesMap.put(entity.getId(), entity);
        }
    }

    public TYPE findOne(ID id) {
        for (ID key : entitiesMap.keySet()) {
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
            entity.setId((ID) idFactory.newId());
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
        for (ID key : entitiesMap.keySet()) {
            entitiesList.add(entitiesMap.get(key));
        }
    }

    public Map<ID, TYPE> getEntitiesMap() {
        return entitiesMap;
    }


    public void setEntitiesMap(Map<ID, TYPE> entitiesMap) {
        this.entitiesMap.clear();

        if (entitiesMap == null) {
            return;
        }
        this.entitiesMap = entitiesMap;
        refreshEntitiesListFromMap();
    }

    public IdFactory getIdFactory() {
        return idFactory;
    }

    public void setIdFactory(IdFactory idFactory) {
        this.idFactory = idFactory;
    }

    public class UUIDFactory implements IdFactory {

        @Override
        public Object newId() {
            return UUID.randomUUID().toString();
        }
    }

    public interface IdFactory {
        Object newId();
    }
}
