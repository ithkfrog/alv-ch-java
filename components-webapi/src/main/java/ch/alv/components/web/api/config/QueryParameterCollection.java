package ch.alv.components.web.api.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration of a reusable query parameter collection.
 *
 * @since 1.0.0
 */
public class QueryParameterCollection {

    private String name;

    private List<QueryParameter> parameters = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QueryParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<QueryParameter> parameters) {
        this.parameters = parameters;
    }

}
