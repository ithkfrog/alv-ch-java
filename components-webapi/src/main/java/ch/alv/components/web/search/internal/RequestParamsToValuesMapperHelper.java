package ch.alv.components.web.search.internal;

import ch.alv.components.core.utils.ConversionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Helper for implementations of the {@link ch.alv.components.web.search.RequestParamsToValuesMapper} interface.
 *
 * @since 1.0.0
 */
public class RequestParamsToValuesMapperHelper {

    public String getStringSourceValue(String key, Map<String, String[]> source) {
        return getSingleSourceValue(key, source, String.class);
    }

    public <T> T getSingleSourceValue(String key, Map<String, String[]> source, Class<T> type) {
        if (source == null) {
            return null;
        }
        if (!source.containsKey(key)) {
            return null;
        }
        String[] values = source.get(key);
        if (values.length == 0) {
            return null;
        }
        return convert(values[0], type);
    }

    public <T> List<T> getMultiSourceValue(String key, Map<String, String[]> source, Class<T> type) {
        if (source == null) {
            return null;
        }
        if (!source.containsKey(key)) {
            return null;
        }
        if (type == null) {
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
