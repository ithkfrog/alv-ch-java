package ch.alv.components.web.search;

import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.ValuesProvider;

/**
 * Web specific extension of the {@link Search} interface.
 *
 * @since 1.0.0
 */
public interface WebSearch extends Search {

    Class<? extends ValuesProvider> getParamValuesProviderClass();

}
