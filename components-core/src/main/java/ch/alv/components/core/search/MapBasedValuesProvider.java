package ch.alv.components.core.search;

import ch.alv.components.core.utils.ConversionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base implementation of the {@link ch.alv.components.core.search.ValuesProvider} interface.
 *
 * @since 1.0.0
 */
public class MapBasedValuesProvider implements ValuesProvider {

    private final Map<String, String[]> values = new HashMap<>();

    public MapBasedValuesProvider() {
    }

    public MapBasedValuesProvider(Map<String, String[]> values) {
        setValues(values);
    }

    public void setValues(Map<String, String[]> values) {
        this.values.clear();
        if (values != null) {
            this.values.putAll(values);
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see ch.alv.components.core.search.ValuesProvider#getValues)
     */
    @Override
    public Map<String, String[]> getValues() {
        return values;
    }

    public String getStringValue(String key) {
        return getSingleValue(key, String.class);
    }

    public <T> T getSingleValue(String key, Class<T> type) {
        if (!values.containsKey(key)) {
            return null;
        }
        String[] vals = values.get(key);
        if (vals == null || vals.length == 0) {
            return null;
        }
        return convert(vals[0], type);
    }

    public <T> List<T> getMultiValue(String key, Class<T> type) {
        if (!values.containsKey(key)) {
            return null;
        }
        if (type == null) {
            return null;
        }
        String[] vals = values.get(key);
        if (vals.length == 0) {
            return null;
        }
        List<T> target = new ArrayList<>();
        for (String value : vals) {
            target.add(convert(value, type));
        }
        return target;
    }

    private <T> T convert(String value, Class<T> type) {
        return ConversionUtils.convert(value, type);
    }

}
