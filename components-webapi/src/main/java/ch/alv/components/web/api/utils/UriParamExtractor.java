package ch.alv.components.web.api.utils;

import ch.alv.components.core.utils.KeyValue;
import ch.alv.components.core.utils.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to handle uriParams in a request string
 *
 * @since 1.0.0
 */
public class UriParamExtractor {

    private final String[] uriTemplateParts;

    private String startMarker = "{";

    private String endMarker = "}";

    private String[] idParamNames = new String[] { "id", "itemId" };

    public UriParamExtractor(String uriTemplate) {
        this.uriTemplateParts = uriTemplate.split("/");
    }

    public KeyValue guessIdParam(String uri) {
        for (String key : idParamNames) {
            String value = extractParam(key, uri);
            if (StringHelper.isNotEmpty(value)) {
                return new KeyValue(key, value);
            }
        }
        return null;
    }

    public String extractParam(String paramName, String uri) {
        if (StringHelper.isEmpty(uri)) {
            return null;
        }
        String[] uriParts = uri.split("/");

        int numberOfParts = uriTemplateParts.length;

        if (uriParts.length < numberOfParts) {
            numberOfParts = uriParts.length;
        }

        for (int i = 0; i < numberOfParts; i++) {
            String templateValue = uriTemplateParts[i];
            if (templateValue.startsWith(startMarker) && templateValue.endsWith(endMarker)
                    && templateValue.contains(paramName) ) {
                return uriParts[i];
            }
        }
        return null;
    }

    public Map<String, String[]> extractParams(String uri) {
        Map<String, String[]> params = new HashMap<>();
        if (StringHelper.isEmpty(uri)) {
            return params;
        }
        String[] uriParts = uri.split("/");

        int numberOfParts = uriTemplateParts.length;

        if (uriParts.length < numberOfParts) {
            numberOfParts = uriParts.length;
        }

        for (int i = 0; i < numberOfParts; i++) {
            String templateValue = uriTemplateParts[i];
            if (templateValue.startsWith(startMarker) && templateValue.endsWith(endMarker)
                    && templateValue.length() > 2) {
                String uriValue = uriParts[i];
                params.put(templateValue.substring(1, templateValue.length()-1), new String[] { uriValue });
            }
        }
        return params;
    }

    public String getStartMarker() {
        return startMarker;
    }

    public void setStartMarker(String startMarker) {
        this.startMarker = startMarker;
    }

    public String getEndMarker() {
        return endMarker;
    }

    public void setEndMarker(String endMarker) {
        this.endMarker = endMarker;
    }

    public String[] getIdParamNames() {
        return idParamNames;
    }

    public void setIdParamNames(String[] idParamNames) {
        this.idParamNames = idParamNames;
    }
}
