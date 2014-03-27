package ch.alv.components.web.search;

import ch.alv.components.core.utils.ConversionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base implementation of the {@link WebParamValuesProvider} interface.
 *
 * @since 1.0.0
 */
public abstract class BaseWebParamValuesProvider implements WebParamValuesProvider {

    protected Map<String, String[]> source = new HashMap<>();

    @Override
    public void setSource(Map<String, String[]> source) {
        this.source = source;
    }

    public Map<String, String[]> getSource() {
        return source;
    }

    protected Object getStringValue(String key) {
        return getSingleValue(key, String.class);
    }

    protected Object getSingleValue(String key, Class<?> type) {
        if (!source.containsKey(key)) {
            return null;
        }
        String[] values = source.get(key);
        if (values.length == 0) {
            return null;
        }
        return convert(values[0], type);
    }

    protected Object getMultiValue(String key, Class<?> type) {
        if (!source.containsKey(key)) {
            return null;
        }
        String[] values = source.get(key);
        if (values.length == 0) {
            return null;
        }
        List<Object> target = new ArrayList<>();
        for (String value : values) {
            target.add(convert(value, type));
        }
        return target;
    }

    protected Object convert(String value, Class<?> type) {
        return ConversionUtils.convert(value, type);
    }

}
