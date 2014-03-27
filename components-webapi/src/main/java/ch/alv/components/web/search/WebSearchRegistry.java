package ch.alv.components.web.search;

import ch.alv.components.core.spring.context.DefaultContextProvider;
import ch.alv.components.core.utils.StringHelper;

import java.util.Map;

/**
 * Provides easy access to WebSearches.
 *
 * @since 1.0.0
 */
public class WebSearchRegistry {

    public static WebSearch getWebSearch(String webSearchName) {
        if (StringHelper.isEmpty(webSearchName)) {
            throw new IllegalStateException("Param 'webSearchName' must not be empty.");
        }
        Map<String, WebSearch> configMap = DefaultContextProvider.getBeansOfType(WebSearch.class);
        if (configMap == null || configMap.isEmpty()) {
            return null;
        }
        for (String key : configMap.keySet()) {
            WebSearch search = configMap.get(key);
            if (search.getName().equalsIgnoreCase(webSearchName)) {
                return search;
            }
        }
        return null;
    }

}
