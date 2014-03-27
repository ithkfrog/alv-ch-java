package ch.alv.components.iam.search;

import ch.alv.components.iam.IamConstant;
import ch.alv.components.persistence.repository.ParamValuesProvider;
import ch.alv.components.web.search.BaseWebParamValuesProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ParamValuesProvider} for {@link ch.alv.components.iam.model.User} entities.
 *
 * @since 1.0.0
 */
public class UserSearchParamValuesProvider extends BaseWebParamValuesProvider {

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put(IamConstant.PARAM_USER_NAME, getStringValue("userName"));
        map.put(IamConstant.PARAM_LAST_NAME, getStringValue("lastName"));
        map.put(IamConstant.PARAM_EMAIL, getStringValue("email"));
        return map;
    }

}
