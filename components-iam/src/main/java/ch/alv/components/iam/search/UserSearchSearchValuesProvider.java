package ch.alv.components.iam.search;

import ch.alv.components.iam.IamConstant;
import ch.alv.components.web.search.BaseWebSearchValuesProvider;

/**
 * Custom {@link ch.alv.components.core.search.SearchValuesProvider} for {@link ch.alv.components.iam.model.User} entities.
 *
 * @since 1.0.0
 */
public class UserSearchSearchValuesProvider extends BaseWebSearchValuesProvider {

    @Override
    public void putData() {
        values.put(IamConstant.PARAM_USER_NAME, getStringValue("userName"));
        values.put(IamConstant.PARAM_LAST_NAME, getStringValue("lastName"));
        values.put(IamConstant.PARAM_EMAIL, getStringValue("email"));
    }

}
