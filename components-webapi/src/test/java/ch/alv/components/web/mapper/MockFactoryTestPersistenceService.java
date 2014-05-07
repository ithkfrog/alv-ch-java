package ch.alv.components.web.mapper;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.PersistenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

/**
 * Mock implementation of the {@link PersistenceService} interface
 *
 * @since 1.0.0
 */
public class MockFactoryTestPersistenceService implements PersistenceService<MockFactoryTestEntity, String> {

    private MockFactoryTestEntity entity = new MockFactoryTestEntity();

    @PostConstruct
    private void init() {
        entity.setId("testId");
        entity.setVersion(0);
    }

    @Override
    public MockFactoryTestEntity findOne(String id) {
        if ("testId".equals(id)) {
            return entity;
        }
        return null;
    }

    @Override
    public Page<MockFactoryTestEntity> findAll() {
        return null;  // not required
    }

    @Override
    public Page<MockFactoryTestEntity> findAll(Pageable pageable) {
        return null;  // not required
    }

    @Override
    public MockFactoryTestEntity save(MockFactoryTestEntity item) {
        if (StringHelper.isEmpty(item.getId())) {
            item.setId(UUID.randomUUID().toString());
        }
        return item;
    }

    @Override
    public Page<MockFactoryTestEntity> saveAll(List<MockFactoryTestEntity> items) {
        return null; // not required
    }

    @Override
    public void delete(String id) {
        // not required
    }


}
