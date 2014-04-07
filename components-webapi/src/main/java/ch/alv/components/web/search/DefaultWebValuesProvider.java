package ch.alv.components.web.search;

import ch.alv.components.core.utils.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: put some comment
 *
 * @since 1.0.0
 */
public class DefaultWebValuesProvider extends BaseWebValuesProvider {
    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> values = new HashMap<>();

        String uuid = getStringValue("uuid");
        if (StringHelper.isNotEmpty(uuid)) {
            values.put("uuid", uuid);
        }

        Integer version = getSingleValue("version", Integer.class);
        if (version != null) {
            values.put("version", version);
        }

        String name = getStringValue("name");
        if (StringHelper.isNotEmpty(name)) {
            values.put("name", name);
        }

        return values;
    }
}
