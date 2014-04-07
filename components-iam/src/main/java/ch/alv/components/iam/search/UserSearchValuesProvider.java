package ch.alv.components.iam.search;

import ch.alv.components.iam.IamConstant;
import ch.alv.components.web.search.BaseWebValuesProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ch.alv.components.persistence.search.ValuesProvider} for {@link ch.alv.components.iam.model.User} entities.
 *
 * @since 1.0.0
 */
public class UserSearchValuesProvider extends BaseWebValuesProvider {

    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> map = new HashMap<>();
        map.put(IamConstant.PARAM_USER_NAME, getStringValue("userName"));
        map.put(IamConstant.PARAM_LAST_NAME, getStringValue("lastName"));
        map.put(IamConstant.PARAM_EMAIL, getStringValue("email"));
        return map;
    }

}
