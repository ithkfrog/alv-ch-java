package ch.alv.components.iam.repository;

import ch.alv.components.data.jpa.JpaBaseSearchRepositoryImpl;
import ch.alv.components.iam.model.Role;

/**
 * Default implementation of the {@link RoleRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class RoleRepositoryImpl extends JpaBaseSearchRepositoryImpl<Role> implements RoleRepositoryCustom {
}
