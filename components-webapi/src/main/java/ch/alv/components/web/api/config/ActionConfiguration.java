package ch.alv.components.web.api.config;

import ch.alv.components.web.api.http.HttpProtocol;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds information about an action of an endpoint.
 *
 * @since 1.0.0
 */
public class ActionConfiguration {

    private ActionType type;

    private List<QueryParameter> queryParameters = new ArrayList<>();

    private List<SecurityConfiguration> securityConfigurations = new ArrayList<>();

    private List<UriParameter> baseUriParameters = new ArrayList<>();

    private List<MimeType> body = new ArrayList<>();

    private String description;

    private List<HeaderParameter> headers = new ArrayList<>();

    private List<HttpProtocol> protocols = new ArrayList<>();

    private ResourceConfiguration resource;

    private List<Response> responses = new ArrayList<>();

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public List<QueryParameter> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(List<QueryParameter> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public List<SecurityConfiguration> getSecurityConfigurations() {
        return securityConfigurations;
    }

    public void setSecurityConfigurations(List<SecurityConfiguration> securityConfigurations) {
        this.securityConfigurations = securityConfigurations;
    }

    public List<UriParameter> getBaseUriParameters() {
        return baseUriParameters;
    }

    public void setBaseUriParameters(List<UriParameter> baseUriParameters) {
        this.baseUriParameters = baseUriParameters;
    }

    public List<MimeType> getBody() {
        return body;
    }

    public void setBody(List<MimeType> body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HeaderParameter> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HeaderParameter> headers) {
        this.headers = headers;
    }

    public List<HttpProtocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<HttpProtocol> protocols) {
        this.protocols = protocols;
    }

    public ResourceConfiguration getResource() {
        return resource;
    }

    public void setResource(ResourceConfiguration resource) {
        this.resource = resource;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
