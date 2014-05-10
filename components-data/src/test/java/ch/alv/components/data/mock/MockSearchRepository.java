package ch.alv.components.data.mock;

import ch.alv.components.core.search.SearchQueryFactory;
import ch.alv.components.data.internal.DefaultSearchRepository;

/**
 * Mock for the SearchRepository
 *
 * @since 1.0.0
 */
public class MockSearchRepository extends DefaultSearchRepository<MockModelItem, String> {

    public MockSearchRepository(SearchQueryFactory factory) {
        super(factory, MockModelItem.class, new MockSearchRepositoryAdapter());
    }

}
