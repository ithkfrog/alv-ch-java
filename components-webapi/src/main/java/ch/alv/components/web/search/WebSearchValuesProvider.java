package ch.alv.components.web.search;

import ch.alv.components.core.search.SearchValuesProvider;

import java.util.Map;

/**
 * {@link ch.alv.components.core.search.SearchValuesProvider} which takes a WebRequest param-map as source.
 *
 * @since 1.0.0
 */
public interface WebSearchValuesProvider extends SearchValuesProvider {

    void setSource(Map<String, String[]> params);

}
