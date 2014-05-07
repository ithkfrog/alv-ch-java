package ch.alv.components.iam.repository;

import ch.alv.components.data.jpa.JpaBaseSearchRepository;
import ch.alv.components.iam.model.Permission;

/**
 * Default implementation of the {@link PermissionRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class PermissionRepositoryImpl extends JpaBaseSearchRepository<Permission, String> implements PermissionRepositoryCustom {
}
