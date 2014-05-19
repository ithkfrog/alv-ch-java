package ch.alv.components.iam.query;

import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.query.QueryProvider;

import java.util.Map;

/**
 * JPA ByName search query for the {@link ch.alv.components.iam.model.User} entity
 *
 * @since 1.0.0
 */
public class JpaUserQuery implements QueryProvider {

    @Override
    public String getName() {
        return "userByUsernameQuery";
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T createQuery(ValuesProvider params, Map<String, Object> services, Class<?> entityClass) {
        return (T) ("SELECT u FROM User u where u.username = '" + params.getStringValue("userName") + "'");
    }
}
