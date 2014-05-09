package ch.alv.components.data.mock.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Test Repository interface for {@link ch.alv.components.data.mock.jpa.MockJpaEntity} entities
 *
 * @since 1.0.0
 */
@Repository
public interface MockJpaEntityRepository extends PagingAndSortingRepository<MockJpaEntity, String> {
}
