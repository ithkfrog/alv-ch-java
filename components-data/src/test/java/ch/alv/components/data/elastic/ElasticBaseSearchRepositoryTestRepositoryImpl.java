package ch.alv.components.data.elastic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Default implementation of the {@link ch.alv.components.data.mock.JpaTestRepositoryCustom} interface.
 *
 * @since 1.0.0
 */
public class ElasticBaseSearchRepositoryTestRepositoryImpl extends ElasticBaseSearchRepository<ElasticBaseSearchRepositoryTestEntity, String> implements ElasticBaseSearchRepositoryTestRepositoryCustom {
    @Override
    public Page<ElasticBaseSearchRepositoryTestEntity> getAll(Iterable<String> strings) {
        return getAll((Pageable) null);
    }
}
