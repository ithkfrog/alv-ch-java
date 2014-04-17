package ch.alv.components.web.search;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ch.alv.components.core.search.SearchValuesProvider} that puts all source values as single String values into the values map
 * if the name is not listed in the blackList.
 *
 * @since 1.0.0
 */
public class StringBlackListWebValuesProvider extends BaseWebSearchValuesProvider {

    private List<String> blacklist = new ArrayList<>();

    /**
     * (non-Javadoc)
     *
     * @see ch.alv.components.web.search.BaseWebSearchValuesProvider#putData
     */
    @Override
    protected void putData() {
        for (String key : source.keySet()) {
            if (blacklist.contains(key)) {
                continue;
            }
            values.put(key, getStringSourceValue(key));
        }
    }

    public List<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(List<String> blacklist) {
        this.blacklist = blacklist;
    }

}
