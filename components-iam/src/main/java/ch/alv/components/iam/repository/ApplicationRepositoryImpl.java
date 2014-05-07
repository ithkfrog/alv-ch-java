package ch.alv.components.iam.repository;

import ch.alv.components.data.jpa.JpaBaseSearchRepository;
import ch.alv.components.iam.model.Application;

/**
 * Default implementation of the {@link ApplicationRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class ApplicationRepositoryImpl extends JpaBaseSearchRepository<Application, String> implements ApplicationRepositoryCustom {
}
