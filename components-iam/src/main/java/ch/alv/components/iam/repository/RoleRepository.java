package ch.alv.components.iam.repository;

import ch.alv.components.iam.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Role} entities
 *
 * @since 1.0.0
 */
@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, String>, RoleRepositoryCustom {
}
