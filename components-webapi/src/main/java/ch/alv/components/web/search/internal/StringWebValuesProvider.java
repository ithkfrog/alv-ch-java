package ch.alv.components.web.search.internal;

import java.util.Map;

/**
 * {@link ch.alv.components.core.search.SearchValuesProvider} that puts all source values as single String values into the values map.
 *
 * @since 1.0.0
 */
public class StringWebValuesProvider extends BaseWebSearchValuesProvider {

    public StringWebValuesProvider(Map<String, String[]> source) {
        super(source);
    }

    /**
     * (non-Javadoc)
     *
     * @see BaseWebSearchValuesProvider#putData
     */
    @Override
    protected void putData() {
        for (String key : source.keySet()) {
            values.put(key, getStringSourceValue(key));
        }
    }


}
