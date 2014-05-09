package ch.alv.components.data.elastic;

import ch.alv.components.data.mock.elastic.MockElasticEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Test Repository interface for {@link MockElasticEntity} entities
 *
 * @since 1.0.0
 */
@Repository
public interface ElasticBaseSearchRepositoryTestRepository extends ElasticsearchRepository<MockElasticEntity, String>, ElasticBaseSearchRepositoryTestRepositoryCustom {
}
