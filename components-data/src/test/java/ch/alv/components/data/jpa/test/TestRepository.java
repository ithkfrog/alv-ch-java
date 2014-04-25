package ch.alv.components.data.jpa.test;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Test Repository interface for {@link TestEntity} entities
 *
 * @since 1.0.0
 */
@Repository
public interface TestRepository extends PagingAndSortingRepository<TestEntity, String>, TestRepositoryCustom {
}
