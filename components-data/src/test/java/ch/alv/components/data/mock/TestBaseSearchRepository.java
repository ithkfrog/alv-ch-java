package ch.alv.components.data.mock;

import ch.alv.components.data.internal.BaseSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Mock extension of the {@link BaseSearchRepository} class.
 *
 * @since 1.0.0
 */
public class TestBaseSearchRepository extends BaseSearchRepository<MockModelItem, String> {


    @Override
    protected List<MockModelItem> fetchFromSource(Pageable pageable, Object search) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> getAll(Pageable pageable) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> getAll(Iterable<String> strings) {
        return null;  // not required
    }

    @Override
    public MockModelItem getOne(String s) {
        return null;  // not required
    }
}
