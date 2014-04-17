package ch.alv.components.web.search;

import ch.alv.components.core.utils.StringHelper;

/**
 * A default SearchValuesProvider for web requests.
 *
 * @since 1.0.0
 */
public class DefaultWebSearchValuesProvider extends BaseWebSearchValuesProvider {
    @Override
    public void putData() {
        String uuid = getStringValue("uuid");
        if (StringHelper.isNotEmpty(uuid)) {
            values.put("aUuid", uuid);
        }

        Integer version = getSingleSourceValue("version", Integer.class);
        if (version != null) {
            values.put("aVersion", version);
        }

        String name = getStringValue("name");
        if (StringHelper.isNotEmpty(name)) {
            values.put("aName", name);
        }
    }
}
