package ch.alv.components.data.mock;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.data.DataStoreSearchAdapter;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mock implementation of the {@link DataStoreSearchAdapter} interface.
 *
 * @since 1.0.0
 */
public class MockSearchRepositoryAdapter implements DataStoreSearchAdapter<ModelItem<String, Integer>,String> {

    Map<String, ModelItem<String, Integer>> entitiesMap = new HashMap<>();

    List<ModelItem<String, Integer>> entitiesList = new ArrayList<>();

    public MockSearchRepositoryAdapter() {
        init();
    }

    private void init() {
        entitiesList.add(new MockModelItem<>("a", 0, "a"));
        entitiesList.add(new MockModelItem<>("b", 1, "b"));
        entitiesList.add(new MockModelItem<>("c", 2, "c"));
        entitiesList.add(new MockModelItem<>("d", 3, "d"));
        entitiesList.add(new MockModelItem<>("e", 4, "e"));
        entitiesList.add(new MockModelItem<>("f", 5, "f"));
        entitiesList.add(new MockModelItem<>("g", 6, "g"));
        entitiesList.add(new MockModelItem<>("h", 7, "h"));
        entitiesList.add(new MockModelItem<>("i", 8, "i"));
        entitiesList.add(new MockModelItem<>("k", 9, "k"));
        entitiesList.add(new MockModelItem<>("l", 10, "l"));
        entitiesList.add(new MockModelItem<>("m", 11, "m"));
        entitiesList.add(new MockModelItem<>("n", 12, "n"));
        entitiesList.add(new MockModelItem<>("o", 13, "o"));
        entitiesList.add(new MockModelItem<>("p", 14, "p"));
        entitiesList.add(new MockModelItem<>("q", 15, "q"));
        entitiesList.add(new MockModelItem<>("r", 16, "r"));
        entitiesList.add(new MockModelItem<>("s", 17, "s"));
        entitiesList.add(new MockModelItem<>("t", 18, "t"));

        for (ModelItem<String, Integer> entity : entitiesList) {
            entitiesMap.put(entity.getId(), entity);
        }
    }


    @Override
    public List<ModelItem<String, Integer>> fetchFromSource(Pageable pageable, Object search) {
        return entitiesList;
    }

    @Override
    public ModelItem<String, Integer> fetchFromSource(String id) {
        return entitiesMap.get(id);
    }
}
