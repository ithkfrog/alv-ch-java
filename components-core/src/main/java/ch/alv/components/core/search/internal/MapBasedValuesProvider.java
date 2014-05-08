package ch.alv.components.core.search.internal;

import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.core.utils.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Base implementation of the {@link ch.alv.components.core.search.ValuesProvider} interface.
 *
 * @since 1.0.0
 */
public class MapBasedValuesProvider implements ValuesProvider {

    protected final Map<String, Object> values = new HashMap<>();

    public MapBasedValuesProvider() {
    }

    public MapBasedValuesProvider(Map<String, Object> values) {
        setValues(values);
    }

    public void setValues(Map<String, Object> values) {
        this.values.clear();
        if (values != null) {
            this.values.putAll(values);
        }
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getValues)
     */
    @Override
    public Map<String, Object> getValues() {
        return values;
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getValue(String))
     */
    @Override
    public Object getValue(String name) {
        return getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getValue(String, Object))
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
     * @see ch.alv.components.core.search.ValuesProvider#getStringValue(String)
     */
    @Override
    public String getStringValue(String name) {
        return (String) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getStringValue(String, String)
     */
    @Override
    public String getStringValue(String name, String defaultValue) {
        return (String) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getBooleanValue(String)
     */
    @Override
    public Boolean getBooleanValue(String name) {
        return (Boolean) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getBooleanValue(String, Boolean)
     */
    @Override
    public Boolean getBooleanValue(String name, Boolean defaultValue) {
        return (Boolean) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getIntValue(String))
     */
    @Override
    public Integer getIntValue(String name) {
        return (Integer) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getIntValue(String, Integer)
     */
    @Override
    public Integer getIntValue(String name, Integer defaultValue) {
        return (Integer) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getLongValue(String)
     */
    @Override
    public Long getLongValue(String name) {
        return (Long) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getLongValue(String, Long)
     */
    @Override
    public Long getLongValue(String name, Long defaultValue) {
        return (Long) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getFloatValue(String)
     */
    @Override
    public Float getFloatValue(String name) {
        return (Float) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getFloatValue(String, Float)
     */
    @Override
    public Float getFloatValue(String name, Float defaultValue) {
        return (Float) getValue(name, defaultValue);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getDoubleValue(String)
     */
    @Override
    public Double getDoubleValue(String name) {
        return (Double) getValue(name, null);
    }

    /**
     * (non-Javadoc)
     * @see ch.alv.components.core.search.ValuesProvider#getDoubleValue(String, Double)
     */
    @Override
    public Double getDoubleValue(String name, Double defaultValue) {
        return (Double) getValue(name, defaultValue);
    }
}
