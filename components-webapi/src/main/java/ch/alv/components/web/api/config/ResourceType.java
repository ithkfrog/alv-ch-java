package ch.alv.components.web.api.config;

import ch.alv.components.core.utils.StringHelper;

import java.io.File;
import java.util.Date;

/**
 * Allowed types of parameters.
 *
 * @since 1.0.0
 */
public enum ResourceType {

    STRING(String.class),
    NUMBER(Double.class),
    INTEGER(Integer.class),
    DATE(Date.class),
    FILE(File.class),
    BOOLEAN(Boolean.class),
    OBJECT(Object.class);

    private Class<?> javaClass;

    private ResourceType(Class<?> javaClass) {
        this.javaClass = javaClass;
    }

    public static ResourceType byName(String value) {
        if (StringHelper.isEmpty(value)) {
            return null;
        }
        return valueOf(value.toUpperCase());
    }

    public Class<?> getJavaClass() {
        return javaClass;
    }
}