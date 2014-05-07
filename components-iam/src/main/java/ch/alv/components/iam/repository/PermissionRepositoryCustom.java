package ch.alv.components.iam.repository;

import ch.alv.components.data.SearchRepository;
import ch.alv.components.iam.model.Permission;

/**
 * Custom interface for {@link Permission} entities.
 *
 * @since 1.0.0
 */
public interface PermissionRepositoryCustom extends SearchRepository<Permission, String> {
}
