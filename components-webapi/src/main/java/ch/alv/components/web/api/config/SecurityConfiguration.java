package ch.alv.components.web.api.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of the {@link SecurityConfiguration} interface.
 *
 * @since 1.0.0
 */
public class SecurityConfiguration  {

    private String name;

    private Map<String, List<String>> parameters = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, List<String>> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, List<String>> parameters) {
        this.parameters = parameters;
    }
}
