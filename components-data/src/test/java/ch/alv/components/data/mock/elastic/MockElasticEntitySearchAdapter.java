package ch.alv.components.data.mock.elastic;

import ch.alv.components.data.elastic.ElasticSearchAdapter;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * Elastic mock implementation of the {@link ch.alv.components.data.DataStoreSearchAdapter} repository.
 *
 * @since 1.0.0
 */
public class MockElasticEntitySearchAdapter extends ElasticSearchAdapter<MockElasticEntity, String> {
    public MockElasticEntitySearchAdapter(ElasticsearchTemplate elasticsearchTemplate) {
        super(elasticsearchTemplate, MockElasticEntity.class);
    }
}
