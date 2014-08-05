package ch.alv.components.data.mock;

import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.query.QueryProvider;

import java.util.Map;

/**
 * Mock implementation of a JPA {@link ch.alv.components.data.query.QueryProvider}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockJpaQueryProvider implements QueryProvider {

    public String name = "mockJpaQueryProvider";

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public <T> T createQuery(ValuesProvider params, Map<String, Object> services, Class<?> entityClass) {
        String name = entityClass.getSimpleName();
        String token = name.substring(0,1).toLowerCase();
        return (T) ("select " + token + " from " + name + " " + token);
    }
}
