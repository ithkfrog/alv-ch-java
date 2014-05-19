package ch.alv.components.core.search;

import java.util.List;
import java.util.Map;

/**
 * Provides the params to create search queries.
 *
 * @since 1.0.0
 */
public interface ValuesProvider {

    void setValues(Map<String, String[]> values);

    Map<String, String[]> getValues();

    String getStringValue(String key);

    <T> T getSingleValue(String key, Class<T> type);

    <T> List<T> getMultiValue(String key, Class<T> type);

}
