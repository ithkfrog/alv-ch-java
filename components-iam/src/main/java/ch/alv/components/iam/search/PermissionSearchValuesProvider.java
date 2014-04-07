package ch.alv.components.iam.search;

import ch.alv.components.iam.IamConstant;
import ch.alv.components.web.search.BaseWebValuesProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link ch.alv.components.persistence.search.ValuesProvider} for {@link ch.alv.components.iam.model.Permission} entities.
 *
 * @since 1.0.0
 */
public class PermissionSearchValuesProvider extends BaseWebValuesProvider {

    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> map = new HashMap<>();
        map.put(IamConstant.PARAM_NAME, getStringValue("name"));
        return map;
    }

}
