package ch.alv.components.persistence.repository;

import java.util.Map;

/**
 * Provides the params to create queries.
 *
 * @since 1.0.0
 */
public interface ParamValuesProvider {

    void setSource(Map<String, String[]> source);

    Map<String, Object> getParams();

}
