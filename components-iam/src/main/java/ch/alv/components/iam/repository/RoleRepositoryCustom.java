package ch.alv.components.iam.repository;

import ch.alv.components.data.SearchRepository;
import ch.alv.components.iam.model.Role;

/**
 * Custom interface for {@link Role} entities.
 *
 * @since 1.0.0
 */
public interface RoleRepositoryCustom extends SearchRepository<Role, String> {
}
