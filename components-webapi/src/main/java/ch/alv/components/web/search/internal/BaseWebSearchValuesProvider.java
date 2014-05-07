package ch.alv.components.web.search.internal;

import ch.alv.components.core.search.internal.BaseSearchValuesProvider;
import ch.alv.components.core.utils.ConversionUtils;
import ch.alv.components.web.search.WebSearchValuesProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base implementation of the {@link ch.alv.components.web.search.WebSearchValuesProvider} interface.
 *
 * @since 1.0.0
 */
public abstract class BaseWebSearchValuesProvider extends BaseSearchValuesProvider implements WebSearchValuesProvider {

    protected Map<String, String[]> source = new HashMap<>();

    protected BaseWebSearchValuesProvider() {
        super();
    }

    protected BaseWebSearchValuesProvider(Map<String, String[]> source) {
        super();
        if (source != null) {
            this.source = source;
        }
        putData();
    }

    public void setSource(Map<String, String[]> source) {
        if (source == null) {
            putData();
            return;
        }
        this.source = source;
        putData();
    }

    protected String getStringSourceValue(String key) {
        return getSingleSourceValue(key, String.class);
    }

    protected <T> T getSingleSourceValue(String key, Class<T> type) {
        if (!source.containsKey(key)) {
            return null;
        }
        String[] values = source.get(key);
        if (values.length == 0) {
            return null;
        }
        return convert(values[0], type);
    }

    protected <T> List<T> getMultiSourceValue(String key, Class<T> type) {
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
