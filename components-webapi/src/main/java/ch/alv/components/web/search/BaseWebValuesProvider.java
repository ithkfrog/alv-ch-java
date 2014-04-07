package ch.alv.components.web.search;

import ch.alv.components.core.utils.ConversionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base implementation of the {@link WebValuesProvider} interface.
 *
 * @since 1.0.0
 */
public abstract class BaseWebValuesProvider implements WebValuesProvider {

    protected Map<String, String[]> source = new HashMap<>();

    @Override
    public void setSource(Map<String, String[]> source) {
        this.source = source;
    }

    public Map<String, String[]> getSource() {
        return source;
    }

    protected String getStringValue(String key) {
        return (String) getSingleValue(key, String.class);
    }

    protected <T> T getSingleValue(String key, Class<T> type) {
        if (!source.containsKey(key)) {
            return null;
        }
        String[] values = source.get(key);
        if (values.length == 0) {
            return null;
        }
        return convert(values[0], type);
    }

    protected <T> List<T> getMultiValue(String key, Class<T> type) {
        if (!source.containsKey(key)) {
            return null;
        }
        String[] values = source.get(key);
        if (values.length == 0) {
            return null;
        }
        List<T> target = new ArrayList<>();
        for (String value : values) {
            target.add(convert(value, type));
        }
        return target;
    }

    protected <T> T convert(String value, Class<T> type) {
        return ConversionUtils.convert(value, type);
    }

}
