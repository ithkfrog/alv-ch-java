package ch.alv.components.data.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Test Repository interface for {@link ch.alv.components.data.mock.JpaTestEntity} entities
 *
 * @since 1.0.0
 */
@Repository
public interface ElasticBaseSearchRepositoryTestRepository extends ElasticsearchRepository<ElasticBaseSearchRepositoryTestEntity, String>, ElasticBaseSearchRepositoryTestRepositoryCustom {
}
