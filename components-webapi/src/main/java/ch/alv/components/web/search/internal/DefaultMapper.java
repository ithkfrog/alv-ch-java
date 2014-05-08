package ch.alv.components.web.search.internal;

import ch.alv.components.web.search.RequestParamsToValuesMapper;

import java.util.Map;

/**
 * A default {@link ch.alv.components.web.search.RequestParamsToValuesMapper} for web requests.
 *
 * @since 1.0.0
 */
public class DefaultMapper implements RequestParamsToValuesMapper {

    /**
     * (non-Javadoc)
     * @see ch.alv.components.web.search.RequestParamsToValuesMapper#map
     */
    @Override
    public void map(Map<String, String[]> source, Map<String, Object> target) {
        if (source == null) {
            return;
        }
        RequestParamsToValuesMapperHelper helper = new RequestParamsToValuesMapperHelper();
        for (String key : source.keySet()) {
            target.put(key, helper.getStringSourceValue(key, source));
        }
    }
}
