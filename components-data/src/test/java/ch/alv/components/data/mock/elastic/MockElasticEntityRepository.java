package ch.alv.components.data.mock.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the {@link MockElasticEntity} class.
 *
 * @since 1.0.0
 */
@Repository
public interface MockElasticEntityRepository extends ElasticsearchRepository<MockElasticEntity, String> {
}
