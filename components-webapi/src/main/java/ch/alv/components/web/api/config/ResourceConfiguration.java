package ch.alv.components.web.api.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the {@link ResourceConfiguration} interface.
 *
 * @since 1.0.0
 */
public class ResourceConfiguration  {

    private static final String[] ID_PARAM_PATTERNS = new String[]{"id", ".*[Id]+"};

    private String name;

    private String description;

    private String uri;

    private String relativeUri;

    private String parentUri;

    private String url;

    private List<ActionConfiguration> actions = new ArrayList<>();

    private Class<?> resourceType;

    private ResourceConfiguration parentResource;

    private List<UriParameter> uriParameters = new ArrayList<>();

    private List<QueryParameter> queryParameters = new ArrayList<>();

    private List<UriParameter> baseUriParameters = new ArrayList<>();

    private List<ResourceConfiguration> children = new ArrayList<>();

    private List<UriParameter> resolvedUriParameters = new ArrayList<>();

    private List<SecurityConfiguration> securityConfigurations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRelativeUri() {
        return relativeUri;
    }

    public void setRelativeUri(String relativeUri) {
        this.relativeUri = relativeUri;
    }

    public String getParentUri() {
        return parentUri;
    }

    public void setParentUri(String parentUri) {
        this.parentUri = parentUri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ActionConfiguration> getActions() {
        return actions;
    }

    public void setActions(List<ActionConfiguration> actions) {
        this.actions = actions;
    }

    public Class<?> getResourceType() {
        return resourceType;
    }

    public void setResourceType(Class<?> resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceConfiguration getParentResource() {
        return parentResource;
    }

    public void setParentResource(ResourceConfiguration parentResource) {
        this.parentResource = parentResource;
    }

    public List<ResourceConfiguration> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceConfiguration> children) {
        this.children = children;
    }

    public List<UriParameter> getUriParameters() {
        return uriParameters;
    }

    public void setUriParameters(List<UriParameter> uriParameters) {
        this.uriParameters = uriParameters;
    }

    public List<QueryParameter> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(List<QueryParameter> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public List<UriParameter> getBaseUriParameters() {
        return baseUriParameters;
    }

    public void setBaseUriParameters(List<UriParameter> baseUriParameters) {
        this.baseUriParameters = baseUriParameters;
    }

    public boolean hasIdUriParam() {
        for (String pattern : ID_PARAM_PATTERNS) {
            for (UriParameter param : uriParameters)
            if (param.getName().matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasUriParams() {
        return !uriParameters.isEmpty();
    }

    public List<String> getUriParamNames() {
        List<String> paramNames = new ArrayList<>();
        for (UriParameter param : uriParameters) {
            paramNames.add(param.getName());
        }
        return paramNames;
    }

    public boolean hasIdQueryParameter() {
        for (QueryParameter parameter : queryParameters) {
            for (String pattern : ID_PARAM_PATTERNS) {
                if (parameter.getName().matches(pattern)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<UriParameter> getResolvedUriParameters() {
        return resolvedUriParameters;
    }

    public void setResolvedUriParameters(List<UriParameter> resolvedUriParameters) {
        this.resolvedUriParameters = resolvedUriParameters;
    }

    public List<SecurityConfiguration> getSecurityConfigurations() {
        return securityConfigurations;
    }

    public void setSecurityConfigurations(List<SecurityConfiguration> securityConfigurations) {
        this.securityConfigurations = securityConfigurations;
    }

}
