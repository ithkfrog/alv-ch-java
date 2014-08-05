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

    private String name;

    private String staticQuery;

    @Override
    @SuppressWarnings("unchecked")
    public <T> T createQuery(ValuesProvider params, Map<String, Object> services, Class<?> entityClass) {
        return (T) staticQuery;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaticQuery() {
        return staticQuery;
    }

    public void setStaticQuery(String staticQuery) {
        this.staticQuery = staticQuery;
    }
}
