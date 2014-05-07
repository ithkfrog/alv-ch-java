package ch.alv.components.web.search.internal;

import ch.alv.components.core.utils.StringHelper;

import java.util.Map;

/**
 * A default SearchValuesProvider for web requests.
 *
 * @since 1.0.0
 */
public class DefaultWebSearchValuesProvider extends BaseWebSearchValuesProvider {

    public DefaultWebSearchValuesProvider() {
        super();
    }

    public DefaultWebSearchValuesProvider(Map<String, String[]> source) {
        super(source);
    }

    @Override
    public void putData() {
        String uuid = getStringSourceValue("uuid");
        if (StringHelper.isNotEmpty(uuid)) {
            values.put("uuid", uuid);
        }

        Integer version = getSingleSourceValue("version", Integer.class);
        if (version != null) {
            values.put("version", version);
        }

        String name = getStringSourceValue("name");
        if (StringHelper.isNotEmpty(name)) {
            values.put("name", name);
        }
    }
}
