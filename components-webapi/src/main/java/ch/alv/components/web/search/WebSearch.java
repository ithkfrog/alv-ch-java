package ch.alv.components.web.search;

import ch.alv.components.persistence.repository.ParamValuesProvider;
import ch.alv.components.persistence.search.Search;

/**
 * Web specific extension of the {@link Search} interface.
 *
 * @since 1.0.0
 */
public interface WebSearch extends Search {

    Class<? extends ParamValuesProvider> getParamValuesProviderClass();

}
