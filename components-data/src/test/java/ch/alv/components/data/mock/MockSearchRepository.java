package ch.alv.components.data.mock;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.data.internal.DefaultSearchRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mock for the SearchRepository
 *
 * @since 1.0.0
 */
public class MockSearchRepository extends DefaultSearchRepository<ModelItem, String> {

    Map<String, ModelItem> entitiesMap = new HashMap<>();

    List<ModelItem> entitiesList = new ArrayList<>();

    public MockSearchRepository() {
        super(new MockSearchRepositoryAdapter());
    }

}
