package ch.alv.components.data.mock;

import ch.alv.components.data.jpa.JpaBaseSearchRepository;

/**
 * Default implementation of the {@link JpaTestRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class JpaTestRepositoryImpl extends JpaBaseSearchRepository<JpaTestEntity, String> implements JpaTestRepositoryCustom {
}
