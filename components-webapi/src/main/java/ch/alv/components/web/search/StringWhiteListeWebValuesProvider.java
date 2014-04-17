package ch.alv.components.web.search;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ch.alv.components.core.search.SearchValuesProvider} that puts all source values as single String values into the values map
 * if the name is listed in the whiteList.
 *
 * @since 1.0.0
 */
public class StringWhiteListeWebValuesProvider extends BaseWebSearchValuesProvider {

    private List<String> whiteList = new ArrayList<>();

    /**
     * (non-Javadoc)
     *
     * @see BaseWebSearchValuesProvider#putData
     */
    @Override
    protected void putData() {
        for (String key : source.keySet()) {
            if (!whiteList.contains(key)) {
                continue;
            }
            values.put(key, getStringSourceValue(key));
        }
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

}
