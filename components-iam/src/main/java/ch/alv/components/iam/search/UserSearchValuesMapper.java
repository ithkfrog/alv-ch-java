package ch.alv.components.iam.search;

import ch.alv.components.iam.IamConstant;
import ch.alv.components.web.search.RequestParamsToValuesMapper;
import ch.alv.components.web.search.internal.RequestParamsToValuesMapperHelper;

import java.util.Map;

/**
 * Custom {@link ch.alv.components.core.search.ValuesProvider} for {@link ch.alv.components.iam.model.User} entities.
 *
 * @since 1.0.0
 */
public class UserSearchValuesMapper implements RequestParamsToValuesMapper {

    private RequestParamsToValuesMapperHelper helper = new RequestParamsToValuesMapperHelper();

    @Override
    public void map(Map<String, String[]> source, Map<String, Object> target) {
        target.put(IamConstant.PARAM_USER_NAME, helper.getStringSourceValue("userName", source));
        target.put(IamConstant.PARAM_LAST_NAME, helper.getStringSourceValue("lastName", source));
        target.put(IamConstant.PARAM_EMAIL, helper.getStringSourceValue("email", source));
    }
}
