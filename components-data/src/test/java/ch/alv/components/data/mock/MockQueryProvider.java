package ch.alv.components.data.mock;

import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.query.QueryProvider;

import java.util.Map;

/**
 * Mock implementation of the {@link QueryProvider} interface.
 *
 * @since 1.0.0
 */
public class MockQueryProvider implements QueryProvider {
    @Override
    public String getName() {
        return "mockQueryProvider";
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T createQuery(ValuesProvider params, Map<String, Object> services, Class<?> entityClass) {
        return (T) "testQuery";
    }
}
