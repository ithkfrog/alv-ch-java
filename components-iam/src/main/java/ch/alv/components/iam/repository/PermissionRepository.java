package ch.alv.components.iam.repository;

import ch.alv.components.iam.model.Permission;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Permission} entities
 *
 * @since 1.0.0
 */
@Repository
public interface PermissionRepository extends PagingAndSortingRepository<Permission, String>, PermissionRepositoryCustom {
}
