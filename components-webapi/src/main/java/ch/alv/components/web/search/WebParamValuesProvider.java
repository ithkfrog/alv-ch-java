package ch.alv.components.web.search;

import ch.alv.components.persistence.repository.ParamValuesProvider;

import java.util.Map;

/**
 * {@link ParamValuesProvider} which takes a WebRequest param-map as source.
 *
 * @since 1.0.0
 */
public interface WebParamValuesProvider extends ParamValuesProvider {

    void setSource(Map<String, String[]> params);

}
