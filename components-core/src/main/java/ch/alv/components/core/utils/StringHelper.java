package ch.alv.components.core.utils;

import org.springframework.util.StringUtils;

/**
 * To enforce the usage of the spring StringUtils those are wrapped in this class. We name it explicitly StringHelper to
 * create a distinction to other StringUtils classes.
 *
 * @since 1.0.0
 */
public class StringHelper extends StringUtils {

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

}
