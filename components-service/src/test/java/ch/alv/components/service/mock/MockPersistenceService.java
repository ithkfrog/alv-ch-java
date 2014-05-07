package ch.alv.components.service.mock;

import ch.alv.components.service.PersistenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service stub for testing purposes.
 *
 * @since 1.0.0
 */
public class MockPersistenceService implements PersistenceService<MockModelItem, String> {

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
    public MockModelItem save(MockModelItem item) {
        return null;  // not required
    }

    @Override
    public Page<MockModelItem> saveAll(List<MockModelItem> items) {
        return null;  // not required
    }

    @Override
    public void delete(String id) {
        // not required
    }
}
