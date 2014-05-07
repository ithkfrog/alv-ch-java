package ch.alv.components.service.mock;

import ch.alv.components.core.search.SearchValuesProvider;
import ch.alv.components.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service stub for testing purposes.
 *
 * @since 1.0.0
 */
public class MockSearchService implements SearchService<MockModelItem, String> {

    @Override
    public MockModelItem findOne(String id) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> findAll() {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> findAll(Pageable pageable) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> find(SearchValuesProvider searchValuesProvider) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> find(String searchName, SearchValuesProvider searchValuesProvider) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> find(Pageable pageable, SearchValuesProvider searchValuesProvider) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> find(Pageable pageable, String searchName, SearchValuesProvider searchValuesProvider) {
        return null;  // not required
    }
}
