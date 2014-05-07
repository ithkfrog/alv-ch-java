package ch.alv.components.data.mock;

import ch.alv.components.data.jpa.JpaBaseSearchRepository;

/**
 * Default implementation of the {@link ch.alv.components.data.mock.JpaTestRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class JpaBaseRepositoryTestRepositoryImpl extends JpaBaseSearchRepository<JpaBaseRepositoryTestEntity, String> implements JpaBaseRepositoryTestRepositoryCustom {
}
