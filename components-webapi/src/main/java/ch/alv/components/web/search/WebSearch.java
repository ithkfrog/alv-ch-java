package ch.alv.components.web.search;

import ch.alv.components.persistence.search.Search;
import ch.alv.components.persistence.search.ValuesProvider;

/**
 * Web specific extension of the {@link ch.alv.components.persistence.search.DynamicSearch} interface.
 *
 * @since 1.0.0
 */
public interface WebSearch extends Search {

    Class<? extends ValuesProvider> getParamValuesProviderClass();

}
