package ch.alv.components.persistence.repository;

import java.util.Map;

/**
 * Provides the params to create queries.
 *
 * @since 1.0.0
 */
public interface ParamValuesProvider {

    Map<String, Object> getParams();

}
