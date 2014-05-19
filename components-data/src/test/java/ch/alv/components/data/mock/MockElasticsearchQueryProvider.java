package ch.alv.components.data.mock;

import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.query.QueryProvider;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * Mock implementation of an Elasticsearch {@link QueryProvider}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockElasticsearchQueryProvider implements QueryProvider {

    public static final String NAME = "mockElasticsearchQueryProvider";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public <T> T createQuery(ValuesProvider params, Map<String, Object> services, Class<?> entityClass) {
        return (T) new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
    }
}
