package ch.alv.components.iam.repository;

import ch.alv.components.data.search.SearchRepository;
import ch.alv.components.iam.model.User;

/**
 * Custom interface for {@link User} entities.
 *
 * @since 1.0.0
 */
public interface UserRepositoryCustom extends SearchRepository<User> {
}
