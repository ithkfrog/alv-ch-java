package ch.alv.components.iam.repository;

import ch.alv.components.iam.model.User;
import ch.alv.components.persistence.repository.CustomRepositoryImpl;

/**
 * Default implementation of the {@link UserRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class UserRepositoryImpl extends CustomRepositoryImpl<User> implements UserRepositoryCustom {
}
