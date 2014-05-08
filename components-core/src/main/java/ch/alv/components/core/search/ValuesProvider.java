package ch.alv.components.core.search;

import java.util.Map;

/**
 * Provides the params to create search queries.
 *
 * @since 1.0.0
 */
public interface ValuesProvider {

    /**
     * Getter
     * @return Map of all values.
     */
    Map<String, Object> getValues();

    /**
     * Getter
     * @return Object carrying the given name or null.
     */
    Object getValue(String name);

    /**
     * Getter with default value
     * @return Object carrying the given name or, if null, the provided defaultValue.
     */
    Object getValue(String name, Object defaultValue);

    /**
     * Getter
     * @return String carrying the given name or null.
     */
    String getStringValue(String name);

    /**
     * Getter with default value
     * @return String carrying the given name or, if null, the provided defaultValue.
     */
    String getStringValue(String name, String defaultValue);

    /**
     * Getter
     * @return Boolean carrying the given name or null.
     */
    Boolean getBooleanValue(String name);

    /**
     * Getter with default value
     * @return Boolean carrying the given name or, if null, the provided defaultValue.
     */
    Boolean getBooleanValue(String name, Boolean defaultValue);

    /**
     * Getter
     * @return Integer carrying the given name or null.
     */
    Integer getIntValue(String name);

    /**
     * Getter with default value
     * @return Integer carrying the given name or, if null, the provided defaultValue.
     */
    Integer getIntValue(String name, Integer defaultValue);

    /**
     * Getter
     * @return Long carrying the given name or null.
     */
    Long getLongValue(String name);

    /**
     * Getter with default value
     * @return Long carrying the given name or, if null, the provided defaultValue.
     */
    Long getLongValue(String name, Long defaultValue);

    /**
     * Getter
     * @return Float carrying the given name or null.
     */
    Float getFloatValue(String name);

    /**
     * Getter with default value
     * @return Float carrying the given name or, if null, the provided defaultValue.
     */
    Float getFloatValue(String name, Float defaultValue);

    /**
     * Getter
     * @return Double carrying the given name or null.
     */
    Double getDoubleValue(String name);

    /**
     * Getter with default value
     * @return Double carrying the given name or, if null, the provided defaultValue.
     */
    Double getDoubleValue(String name, Double defaultValue);

}
