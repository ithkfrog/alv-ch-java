package ch.alv.components.persistence.search;

import java.util.Map;

/**
 * Provides the params to create queries.
 *
 * @since 1.0.0
 */
public interface ValuesProvider {

    Map<String, Object> getValues();

}
