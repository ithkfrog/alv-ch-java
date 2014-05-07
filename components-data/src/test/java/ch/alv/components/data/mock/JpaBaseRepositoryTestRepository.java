package ch.alv.components.data.mock;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Test Repository interface for {@link ch.alv.components.data.mock.JpaTestEntity} entities
 *
 * @since 1.0.0
 */
@Repository
public interface JpaBaseRepositoryTestRepository extends PagingAndSortingRepository<JpaBaseRepositoryTestEntity, String>, JpaBaseRepositoryTestRepositoryCustom {
}
