package ch.alv.components.iam.repository;

import ch.alv.components.data.jpa.JpaBaseSearchRepository;
import ch.alv.components.iam.model.Role;

/**
 * Default implementation of the {@link RoleRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class RoleRepositoryImpl extends JpaBaseSearchRepository<Role, String> implements RoleRepositoryCustom {
}
