package ch.alv.components.core.search;

import java.util.Map;

/**
 * Provides the params to create searches.
 *
 * @since 1.0.0
 */
public interface ValuesProvider {

    Map<String, Object> getValues();

}
