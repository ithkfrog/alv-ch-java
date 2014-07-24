package ch.alv.components.web.api.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps Java classes to request uris.
 *
 * @since 1.0.0
 */
public class UriToJavaClassMapping {

    private Map<String, Class<?>> mapping = new HashMap<>();

    public UriToJavaClassMapping() {
    }

    public UriToJavaClassMapping(Map<String, Class<?>> mapping) {
        if (mapping == null) {
            return;
        }
        this.mapping = mapping;
    }

    public Map<String, Class<?>> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, Class<?>> mapping) {
        this.mapping = mapping;
    }

    public Class<?> getClassForUri(String uri) {
        for (String key : mapping.keySet()) {
            if (uri.matches(key)) {
                return mapping.get(key);
            }
        }
        return null;
    }
}
