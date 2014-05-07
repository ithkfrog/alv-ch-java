package ch.alv.components.iam.search;

import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.SearchValuesProvider;

/**
 * ByName search query for the {@link ch.alv.components.iam.model.User} entity
 *
 * @since 1.0.0
 */
public class UserSearch implements Search {

    @Override
    public Object createQuery(SearchValuesProvider searchValuesProvider, Class<?> targetClass) {
        return "SELECT u FROM User u where u.username = '" + searchValuesProvider.getStringValue("userName") + "'";
    }

    @Override
    public String getName() {
        return "userByUsernameSearch";
    }
}
