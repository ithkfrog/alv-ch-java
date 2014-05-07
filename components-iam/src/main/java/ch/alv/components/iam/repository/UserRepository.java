package ch.alv.components.iam.repository;

import ch.alv.components.iam.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link User} entities
 *
 * @since 1.0.0
 */
@Repository(value = "userRepository")
public interface UserRepository extends PagingAndSortingRepository<User, String>, UserRepositoryCustom {
}
