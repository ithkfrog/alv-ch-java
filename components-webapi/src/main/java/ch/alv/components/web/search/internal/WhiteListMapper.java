
package ch.alv.components.web.search.internal;

import ch.alv.components.web.search.RequestParamsToValuesMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link ch.alv.components.core.search.ValuesProvider} that puts all source values as single String values into the values map
 * if the name is listed in the whiteList.
 *
 * @since 1.0.0
 */
public class WhiteListMapper implements RequestParamsToValuesMapper {

    private final List<String> whiteList = new ArrayList<>();

    /**
     * Constructor
     * @param whiteList a list of allowed param names
     */
    public WhiteListMapper(List<String> whiteList) {
        if (whiteList != null) {
            this.whiteList.addAll(whiteList);
        }
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.web.search.RequestParamsToValuesMapper#map
     */
    @Override
    public void map(Map<String, String[]> source, Map<String, Object> target) {
        RequestParamsToValuesMapperHelper helper = new RequestParamsToValuesMapperHelper();
        for (String key : source.keySet()) {
            if (!whiteList.contains(key)) {
                continue;
            }
            target.put(key, helper.getStringSourceValue(key, source));
        }
    }
}
