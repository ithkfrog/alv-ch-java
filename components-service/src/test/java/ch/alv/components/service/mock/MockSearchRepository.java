package ch.alv.components.service.mock;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.data.internal.BaseSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mock for the SearchRepository
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockSearchRepository extends BaseSearchRepository<ModelItem, String> {


    Map<String, ModelItem> entitiesMap = new HashMap<>();

    List<ModelItem> entitiesList = new ArrayList<>();

    @PostConstruct
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
    protected List fetchFromSource(Pageable pageable, Object search) {
        return entitiesList;
    }

    @Override
    public Page<ModelItem> getAll(Pageable pageable) {
        return createPage(pageable, entitiesList);
    }

    @Override

    public Page<ModelItem> getAll(Iterable<String> ids) {
        List<ModelItem> list = new ArrayList<>();
        for (String id : ids) {
            list.add(getOne(id));
        }
        return createPage(null, list);
    }

    @Override
    public ModelItem getOne(String id) {
        return entitiesMap.get(id);
    }
}
