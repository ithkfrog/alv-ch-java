package ch.alv.components.iam.repository;

import ch.alv.components.data.jpa.JpaBaseSearchRepositoryImpl;
import ch.alv.components.iam.model.Permission;

/**
 * Default implementation of the {@link PermissionRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class PermissionRepositoryImpl extends JpaBaseSearchRepositoryImpl<Permission> implements PermissionRepositoryCustom {
}
