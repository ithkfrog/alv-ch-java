package ch.alv.components.data.mock;

import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.query.QueryProvider;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;

/**
 * Mock implementation of an MongoDb {@link ch.alv.components.data.query.QueryProvider}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockMongoDbQueryProvider implements QueryProvider {

    public static final String NAME = "mockMongoDbQueryProvider";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public <T> T createQuery(ValuesProvider params, Map<String, Object> services, Class<?> entityClass) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(params.getStringValue("name")));
        return (T) query;
    }
}
