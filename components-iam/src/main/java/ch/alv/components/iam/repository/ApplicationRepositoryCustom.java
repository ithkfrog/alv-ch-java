package ch.alv.components.iam.repository;

import ch.alv.components.data.SearchRepository;
import ch.alv.components.iam.model.Application;

/**
 * Custom interface for {@link Application} entities.
 *
 * @since 1.0.0
 */
public interface ApplicationRepositoryCustom extends SearchRepository<Application, String> {
}
