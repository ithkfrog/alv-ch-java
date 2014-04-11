package ch.alv.components.iam.repository;

import ch.alv.components.data.jpa.JpaBaseSearchRepositoryImpl;
import ch.alv.components.iam.model.Application;

/**
 * Default implementation of the {@link ApplicationRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class ApplicationRepositoryImpl extends JpaBaseSearchRepositoryImpl<Application> implements ApplicationRepositoryCustom {
}
