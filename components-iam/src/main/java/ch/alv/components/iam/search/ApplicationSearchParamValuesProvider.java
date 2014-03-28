package ch.alv.components.iam.search;

import ch.alv.components.iam.IamConstant;
import ch.alv.components.web.search.BaseWebParamValuesProvider;
import ch.alv.components.web.search.WebParamValuesProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ch.alv.components.persistence.repository.ParamValuesProvider} for {@link ch.alv.components.iam.model.Application} entities.
 *
 * @since 1.0.0
 */
public class ApplicationSearchParamValuesProvider extends BaseWebParamValuesProvider implements WebParamValuesProvider {

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put(IamConstant.PARAM_NAME, getStringValue("name"));
        return map;
    }

}
