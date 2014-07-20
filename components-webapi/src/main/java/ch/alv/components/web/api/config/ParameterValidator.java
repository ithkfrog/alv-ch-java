package ch.alv.components.web.api.config;

import ch.alv.components.core.utils.StringHelper;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;

/**
 * Validator component for parameters.
 *
 * @since 1.0.0
 */
public class ParameterValidator {

    public static boolean validate(BaseParameter baseParameter, String value) {
        if (baseParameter == null || baseParameter.getType() == null) {
            return false;
        }
        switch (baseParameter.getType()) {
            case STRING:
                return validateString(baseParameter, value);
            case NUMBER:
                return validateNumber(baseParameter, value);
            case INTEGER:
                return validateInteger(baseParameter, value);
            case DATE:
                return validateDate(baseParameter, value);
            case FILE:
                return validateFile(baseParameter, value);
            default:
                return validateBoolean(value);
        }
    }

    public static boolean validateString(BaseParameter param, String value) {
        if (param != null) {
            if (StringHelper.isNotEmpty(param.getPattern()) && !value.matches(param.getPattern())) {
                return false;
            }
            if (param.getMinLength() != null && value.length() < param.getMinLength()) {
                return false;
            }
            if (param.getMaxLength() != null && value.length() > param.getMaxLength()) {
                return false;
            }
            if (CollectionUtils.isNotEmpty(param.getEnumeration()) && !param.getEnumeration().contains(value)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateNumber(BaseParameter param, String value) {
        if (param != null) {
            BigDecimal number;
            try {
                number = new BigDecimal(value);
            } catch (NumberFormatException nfe) {
                return false;
            }
            if (param.getMinimum() != null && number.compareTo(param.getMinimum()) < 0) {
                return false;
            }
            if (param.getMaximum() != null && number.compareTo(param.getMaximum()) > 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateInteger(BaseParameter param, String value) {
        if (param != null) {
            Integer number;
            try {
                number = Integer.parseInt(value);
            } catch (NumberFormatException nfe) {
                return false;
            }
            if (param.getMinimum() != null && BigDecimal.valueOf(number).compareTo(param.getMinimum()) < 0) {
                return false;
            }
            if (param.getMaximum() != null && BigDecimal.valueOf(number).compareTo(param.getMaximum()) > 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateDate(BaseParameter param, String value) {
        return true;
    }

    public static boolean validateFile(BaseParameter param, String value) {
        return true;
    }

    public static boolean validateBoolean(String value) {
        return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
    }
}
