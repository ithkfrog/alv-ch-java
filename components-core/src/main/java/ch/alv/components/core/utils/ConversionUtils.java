package ch.alv.components.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Utility class for data conversion concerns.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class ConversionUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ConversionUtils.class);

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
        if (type == Boolean.class || type == boolean.class) {
            return (T) ConversionUtils.toBooleanValue(value, false);
        }
        return null;
    }



    @SuppressWarnings("unchecked, unused")
    public static <T> T convert(String value, Class<T> type, Object defaultValue) {
        if (type == String.class) {
            if (StringHelper.isEmpty(value)) {
                return (T) defaultValue;
            }
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
        if (type == Boolean.class || type == boolean.class) {
            return (T) ConversionUtils.toBooleanValue(value, (Boolean) defaultValue);
        }
        return (T) defaultValue;
    }

    @SuppressWarnings("unchecked")
    public static Integer toIntValue(String stringValue, int defaultValue) {
        try {
            return Integer.valueOf(stringValue);
        } catch (NumberFormatException nfe) {
            LOG.error("Error while converting String value '" + stringValue + "' to Integer -> defaultValue " + defaultValue + " is returned.");
            return defaultValue;
        }
    }

    public static Long toLongValue(String stringValue, long defaultValue) {
        try {
            return Long.valueOf(stringValue);
        } catch (NumberFormatException nfe) {
            LOG.error("Error while converting String value '" + stringValue + "' to Long -> defaultValue " + defaultValue + " is returned.");
            return defaultValue;
        }
    }


    public static Float toFloatValue(String stringValue, float defaultValue) {
        try {
            return Float.valueOf(stringValue);
        } catch (NumberFormatException nfe) {
            LOG.error("Error while converting String value '" + stringValue + "' to Float -> defaultValue " + defaultValue + " is returned.");
            return defaultValue;
        }
    }

    public static Double toDoubleValue(String stringValue, double defaultValue) {
        try {
            return Double.valueOf(stringValue);
        } catch (NumberFormatException nfe) {
            LOG.error("Error while converting String value '" + stringValue + "' to Double -> defaultValue " + defaultValue + " is returned.");
            return defaultValue;
        }
    }

    public static Boolean toBooleanValue(String stringValue, boolean defaultValue) {
        if (StringUtils.isEmpty(stringValue)) {
            return defaultValue;
        }

        if ("false".equalsIgnoreCase(stringValue) || "0".equals(stringValue)) {
            return false;
        }

        if ("true".equalsIgnoreCase(stringValue) || "1".equals(stringValue)) {
            return true;
        }
        return defaultValue;
    }

}
