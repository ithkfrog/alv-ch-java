package ch.alv.components.web.search.internal;

import ch.alv.components.core.utils.ConversionUtils;
import ch.alv.components.core.utils.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Helper for implementations of the {@link ch.alv.components.web.search.RequestParamsToValuesMapper} interface.
 *
 * @since 1.0.0
 */
public class RequestParamsToValuesMapperHelper {

    private final Map<String, String[]> source;
    private final Map<String, Object> target;

    public RequestParamsToValuesMapperHelper() {
        source = null;
        target = null;
    }

    public RequestParamsToValuesMapperHelper(Map<String, String[]> source, Map<String, Object> target) {
        this.source = source;
        this.target = target;
    }

    public Map<String, String[]> getSource() {
        return source;
    }

    public Map<String, Object> getTarget() {
        return target;
    }

    public <T> void mapValueIfNotEmpty(String key, Class<T> type) {
        mapValueIfNotEmpty(key, key, type);
    }

    public <T> void mapValueIfNotEmpty(String sourceKey, String targetKey, Class<T> type) {
        T value = getSingleSourceValue(sourceKey, source, type);
        if (value != null) {
            target.put(targetKey, value);
        }
    }

    public void mapStringIfNotEmpty(String sourceKey, String targetKey) {
        String value = getStringSourceValue(sourceKey, source);
        if (StringHelper.isNotEmpty(value)) {
            target.put(targetKey, value);
        }
    }

    public void mapStringIfNotEmpty(String key) {
        mapStringIfNotEmpty(key, key);
    }

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
        if (values == null || values.length == 0) {
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
