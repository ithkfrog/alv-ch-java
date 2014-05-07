package ch.alv.components.iam.repository;

import ch.alv.components.data.jpa.JpaBaseSearchRepository;
import ch.alv.components.iam.model.User;

/**
 * Default implementation of the {@link UserRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class UserRepositoryImpl extends JpaBaseSearchRepository<User, String> implements UserRepositoryCustom {
}
