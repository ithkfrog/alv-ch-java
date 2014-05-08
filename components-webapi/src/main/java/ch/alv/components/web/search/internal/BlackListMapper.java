package ch.alv.components.web.search.internal;

import ch.alv.components.web.search.RequestParamsToValuesMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link ch.alv.components.core.search.ValuesProvider} that puts all source values as single String values into the values map
 * if the name is not listed in the blackList.
 *
 * @since 1.0.0
 */
public class BlackListMapper implements RequestParamsToValuesMapper {

    private final List<String> blacklist = new ArrayList<>();

    /**
     * Constructor
     * @param blacklist a list of forbidden param names
     */
    public BlackListMapper(List<String> blacklist) {
        if (blacklist != null) {
            this.blacklist.addAll(blacklist);
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
            if (blacklist.contains(key)) {
                continue;
            }
            target.put(key, helper.getStringSourceValue(key, source));
        }
    }
}
