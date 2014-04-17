package ch.alv.components.core.search;

import ch.alv.components.core.utils.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Base implementation of the {@link SearchValuesProvider} interface.
 *
 * @since 1.0.0
 */
public abstract class BaseSearchValuesProvider implements SearchValuesProvider {

    protected Map<String, Object> values = new HashMap<>();

    /**
     * Fill the values map with data.
     */
    protected abstract void putData();

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getValues)
     */
    @Override
    public Map<String, Object> getValues() {
        if (values.isEmpty()) {
            putData();
        }
        return values;
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getValue(String))
     */
    @Override
    public Object getValue(String name) {
        return getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getValue(String, Object))
     */
    @Override
    public Object getValue(String name, Object defaultValue) {
        Object value = getValues().get(name);
        if (value == null || StringHelper.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getStringValue(String)
     */
    @Override
    public String getStringValue(String name) {
        return (String) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getStringValue(String, String)
     */
    @Override
    public String getStringValue(String name, String defaultValue) {
        return (String) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getBooleanValue(String)
     */
    @Override
    public Boolean getBooleanValue(String name) {
        return (Boolean) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getBooleanValue(String, Boolean)
     */
    @Override
    public Boolean getBooleanValue(String name, Boolean defaultValue) {
        return (Boolean) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getIntValue(String))
     */
    @Override
    public Integer getIntValue(String name) {
        return (Integer) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getIntValue(String, Integer)
     */
    @Override
    public Integer getIntValue(String name, Integer defaultValue) {
        return (Integer) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getLongValue(String)
     */
    @Override
    public Long getLongValue(String name) {
        return (Long) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getLongValue(String, Long)
     */
    @Override
    public Long getLongValue(String name, Long defaultValue) {
        return (Long) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getFloatValue(String)
     */
    @Override
    public Float getFloatValue(String name) {
        return (Float) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getFloatValue(String, Float)
     */
    @Override
    public Float getFloatValue(String name, Float defaultValue) {
        return (Float) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getDoubleValue(String)
     */
    @Override
    public Double getDoubleValue(String name) {
        return (Double) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see SearchValuesProvider#getDoubleValue(String, Double)
     */
    @Override
    public Double getDoubleValue(String name, Double defaultValue) {
        return (Double) getValue(name, defaultValue);
    }
}
