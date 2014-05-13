package ch.alv.components.web.mapper;

import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.service.SearchService;
import ch.alv.components.web.response.LinkPageImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Search service mock.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockSearchService implements SearchService<MockFactoryTestEntity, String> {

    Map<String, MockFactoryTestEntity> entitiesMap = new HashMap<>();

    List<MockFactoryTestEntity> entitiesList = new ArrayList<>();

    @PostConstruct
    private void init() {
        entitiesList.add(new MockFactoryTestEntity("a", 0));
        entitiesList.add(new MockFactoryTestEntity("b", 1));
        entitiesList.add(new MockFactoryTestEntity("c", 2));
        entitiesList.add(new MockFactoryTestEntity("d", 3));
        entitiesList.add(new MockFactoryTestEntity("e", 4));
        entitiesList.add(new MockFactoryTestEntity("f", 5));
        entitiesList.add(new MockFactoryTestEntity("g", 6));
        entitiesList.add(new MockFactoryTestEntity("h", 7));
        entitiesList.add(new MockFactoryTestEntity("i", 8));
        entitiesList.add(new MockFactoryTestEntity("k", 9));
        entitiesList.add(new MockFactoryTestEntity("l", 10));
        entitiesList.add(new MockFactoryTestEntity("m", 11));
        entitiesList.add(new MockFactoryTestEntity("n", 12));
        entitiesList.add(new MockFactoryTestEntity("o", 13));
        entitiesList.add(new MockFactoryTestEntity("p", 14));
        entitiesList.add(new MockFactoryTestEntity("q", 15));
        entitiesList.add(new MockFactoryTestEntity("r", 16));
        entitiesList.add(new MockFactoryTestEntity("s", 17));
        entitiesList.add(new MockFactoryTestEntity("t", 18));

        for (MockFactoryTestEntity entity : entitiesList) {
            entitiesMap.put(entity.getId(), entity);
        }
    }

    @Override
    public Page<MockFactoryTestEntity> findAll() {
        return findAll(null);
    }

    @Override
    public Page<MockFactoryTestEntity> findAll(Pageable pageable) {
        return new LinkPageImpl(entitiesList, pageable, 19);
    }

    @Override
    public Page<MockFactoryTestEntity> find(ValuesProvider valuesProvider) {
        List<MockFactoryTestEntity> result = new ArrayList<>();
        result.add(entitiesMap.get(valuesProvider.getValue("id")));
        return new LinkPageImpl(result);
    }

    @Override
    public Page<MockFactoryTestEntity> find(String searchName, ValuesProvider valuesProvider) {
        List<MockFactoryTestEntity> result = new ArrayList<>();
        if ("searchById".equalsIgnoreCase(searchName)) {
            result.add(entitiesMap.get(valuesProvider.getValue("id")));
        }
        if ("searchByVersion".equalsIgnoreCase(searchName)) {
            result.add(entitiesMap.get(valuesProvider.getValue("version")));
        }
        return new LinkPageImpl(result);
    }

    @Override
    public Page<MockFactoryTestEntity> find(Pageable pageable, ValuesProvider valuesProvider) {
        List<MockFactoryTestEntity> result = new ArrayList<>();
        result.add(entitiesMap.get(valuesProvider.getValue("id")));
        return new LinkPageImpl(result, pageable, 1);
    }

    @Override
    public Page<MockFactoryTestEntity> find(Pageable pageable, String searchName, ValuesProvider valuesProvider) {
        List<MockFactoryTestEntity> result = new ArrayList<>();
        if ("searchById".equalsIgnoreCase(searchName)) {
            result.add(entitiesMap.get(valuesProvider.getValue("id")));
        }
        if ("searchByVersion".equalsIgnoreCase(searchName)) {
            result.add(entitiesMap.get(valuesProvider.getValue("version")));
        }
        return new LinkPageImpl(result, pageable, 1);
    }

    @Override
    public MockFactoryTestEntity findOne(String id) {
        return entitiesMap.get(id);
    }

}
