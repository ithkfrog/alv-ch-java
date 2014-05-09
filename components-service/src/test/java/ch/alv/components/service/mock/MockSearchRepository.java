package ch.alv.components.service.mock;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.data.internal.DefaultSearchRepository;

/**
 * Mock for the SearchRepository
 *
 * @since 1.0.0
 */
public class MockSearchRepository extends DefaultSearchRepository<ModelItem, String> {

    public MockSearchRepository() {
        super(new MockSearchRepositoryAdapter());
    }

}
