package ch.alv.components.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for data conversion concerns.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class ConversionUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ConversionUtils.class);

    /**
     * Do not instantiate this utility class
     */
    private ConversionUtils() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T convert(String value, Class<T> type) {
        if (type == String.class) {
            return (T) value;
        }
        if (type == int.class || type == Integer.class) {
            return (T) ConversionUtils.toIntValue(value, 0);
        }
        if (type == long.class || type == Long.class) {
            return (T) ConversionUtils.toLongValue(value, 0);
        }
        if (type == float.class || type == Float.class) {
            return (T) ConversionUtils.toFloatValue(value, 0);
        }
        if (type == double.class || type == Double.class) {
            return (T) ConversionUtils.toDoubleValue(value, 0);
        }
        if (type == boolean.class || type == Boolean.class) {
            return (T) ConversionUtils.toBooleanValue(value, false);
        }
        return null;
    }

    @SuppressWarnings("unchecked, unused")
    public static <T> T convert(String value, Class<T> type, Object defaultValue) {
        if (type == String.class) {
            return (T) value;
        }
        if (type == int.class || type == Integer.class) {
            return (T) ConversionUtils.toIntValue(value, (Integer) defaultValue);
        }
        if (type == long.class || type == Long.class) {
            return (T) ConversionUtils.toLongValue(value, (Long) defaultValue);
        }
        if (type == float.class || type == Float.class) {
            return (T) ConversionUtils.toFloatValue(value, (Float) defaultValue);
        }
        if (type == double.class || type == Double.class) {
            return (T) ConversionUtils.toDoubleValue(value, (Double) defaultValue);
        }
        if (type == boolean.class || type == Boolean.class) {
            return (T) ConversionUtils.toBooleanValue(value, (Boolean) defaultValue);
        }
        return (T) defaultValue;
    }

    @SuppressWarnings("unchecked")
    public static Integer toIntValue(String stringValue, int defaultValue) {
        if (StringHelper.isEmpty(stringValue)) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(stringValue);
        } catch (NumberFormatException nfe) {
            LOG.error("Error while converting String value '" + stringValue + "' to Integer -> defaultValue " + defaultValue + " is returned.");
            return defaultValue;
        }
    }

    public static Long toLongValue(String stringValue, long defaultValue) {
        if (StringHelper.isEmpty(stringValue)) {
            return defaultValue;
        }
        try {
            return Long.valueOf(stringValue);
        } catch (NumberFormatException nfe) {
            LOG.error("Error while converting String value '" + stringValue + "' to Long -> defaultValue " + defaultValue + " is returned.");
            return defaultValue;
        }
    }


    public static Float toFloatValue(String stringValue, float defaultValue) {
        if (StringHelper.isEmpty(stringValue)) {
            return defaultValue;
        }
        try {
            return Float.valueOf(stringValue);
        } catch (NumberFormatException nfe) {
            LOG.error("Error while converting String value '" + stringValue + "' to Float -> defaultValue " + defaultValue + " is returned.");
            return defaultValue;
        }
    }

    public static Double toDoubleValue(String stringValue, double defaultValue) {
        if (StringHelper.isEmpty(stringValue)) {
            return defaultValue;
        }
        try {
            return Double.valueOf(stringValue);
        } catch (NumberFormatException nfe) {
            LOG.error("Error while converting String value '" + stringValue + "' to Double -> defaultValue " + defaultValue + " is returned.");
            return defaultValue;
        }
    }

    public static Boolean toBooleanValue(String stringValue, boolean defaultValue) {
        if (StringHelper.isEmpty(stringValue)) {
            return defaultValue;
        }
        return Boolean.valueOf(stringValue);
    }

}
