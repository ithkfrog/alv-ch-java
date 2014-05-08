package ch.alv.components.service.mock;

import ch.alv.components.core.search.ValuesProvider;
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
    public Page<MockModelItem> find(ValuesProvider valuesProvider) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> find(String searchName, ValuesProvider valuesProvider) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> find(Pageable pageable, ValuesProvider valuesProvider) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> find(Pageable pageable, String searchName, ValuesProvider valuesProvider) {
        return null;  // not required
    }
}
