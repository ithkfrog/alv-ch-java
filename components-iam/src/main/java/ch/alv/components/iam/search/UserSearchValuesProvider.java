package ch.alv.components.iam.search;

import ch.alv.components.iam.IamConstant;
import ch.alv.components.web.search.internal.BaseWebSearchValuesProvider;

/**
 * Custom {@link ch.alv.components.core.search.SearchValuesProvider} for {@link ch.alv.components.iam.model.User} entities.
 *
 * @since 1.0.0
 */
public class UserSearchValuesProvider extends BaseWebSearchValuesProvider {

    @Override
    public void putData() {
        values.put(IamConstant.PARAM_USER_NAME, getStringSourceValue("userName"));
        values.put(IamConstant.PARAM_LAST_NAME, getStringSourceValue("lastName"));
        values.put(IamConstant.PARAM_EMAIL, getStringSourceValue("email"));
    }

}
