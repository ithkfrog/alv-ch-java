package ch.alv.components.web.api.config;

import ch.alv.components.web.api.http.HttpProtocol;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Default implementation of the {@link ApiConfiguration} interface.
 *
 * @since 1.0.0
 */
public class ApiConfiguration {

    private String title;

    private String mediaType;

    private String baseUri;

    private String uri;

    private String basePath;

    private String version;

    private List<HttpProtocol> protocols = new ArrayList<>();

    private List<UriParameter> baseUriParameters = new ArrayList<>();

    private Map<String, String> schemas = new HashMap<>();

    private Collection<QueryParameterCollection> queryParameterCollections = new ArrayList<>();

    private Collection<ResourceConfiguration> resources = new ArrayList<>();

    private Map<String, ResourceType> resourceTypes = new HashMap<>();

    private List<SecurityConfiguration> securityConfigurations = new ArrayList<>();

    private Map<String, ResourceConfiguration> uriResourceMap = new HashMap<>();
    private Map<String, ResourceConfiguration> urlResourceMap = new HashMap<>();
    private Map<String, ResourceConfiguration> nameResourceMap = new HashMap<>();
    private Map<Class<?>, ResourceConfiguration> typeResourceMap = new HashMap<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMediaType() {
        return mediaType;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<HttpProtocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<HttpProtocol> protocols) {
        this.protocols = protocols;
    }

    public List<UriParameter> getBaseUriParameters() {
        return baseUriParameters;
    }

    public void setBaseUriParameters(List<UriParameter> baseUriParameters) {
        this.baseUriParameters = baseUriParameters;
    }

    public Map<String, String> getSchemas() {
        return schemas;
    }

    public void setSchemas(Map<String, String> schemas) {
        this.schemas = schemas;
    }

    public Collection<QueryParameterCollection> getQueryParameterCollections() {
        return queryParameterCollections;
    }

    public void setQueryParameterCollections(Collection<QueryParameterCollection> queryParameterCollections) {
        this.queryParameterCollections = queryParameterCollections;
    }

    public Collection<ResourceConfiguration> getResources() {
        return resources;
    }

    public void setResources(Collection<ResourceConfiguration> resources) {
        this.resources.clear();
        this.uriResourceMap.clear();
        this.urlResourceMap.clear();
        this.nameResourceMap.clear();
        this.typeResourceMap.clear();
        String pattern = "\\{([^}]*?)\\}";
        if (CollectionUtils.isNotEmpty(resources)) {
            for (ResourceConfiguration configuration : resources) {
                if (!this.resources.contains(configuration)) {
                    this.resources.add(configuration);
                    this.uriResourceMap.put(configuration.getUri().replaceAll(pattern, ".*"), configuration);
                    this.nameResourceMap.put(configuration.getName(), configuration);
                    this.typeResourceMap.put(configuration.getResourceType(), configuration);
                }
            }
        }
    }

    public Map<String, ResourceType> getResourceTypes() {
        return resourceTypes;
    }

    public void setResourceTypes(Map<String, ResourceType> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    public List<SecurityConfiguration> getSecurityConfigurations() {
        return securityConfigurations;
    }

    public void setSecurityConfigurations(List<SecurityConfiguration> securityConfigurations) {
        this.securityConfigurations = securityConfigurations;
    }

    public ResourceConfiguration getResourceByUri(String uri) {
        for (String key : uriResourceMap.keySet()) {
            if (uri.matches(key)) {
                return uriResourceMap.get(key);
            }
        }
        throw new NoSuchResourceException("Could not find resource for URI: '" + uri + "'");
    }

/*    public ResourceConfiguration getResourceByUrl(String url) {
        for (String key : urlResourceMap.keySet()) {
            if (url.matches(key)) {
                return urlResourceMap.get(key);
            }
        }
        throw new NoSuchResourceException("Could not find resource for URL: '" + url + "'");
    }*/

    public ResourceConfiguration getResourceByName(String name) {
        if (nameResourceMap.containsKey(name)) {
            return nameResourceMap.get(name);
        }
        throw new NoSuchResourceException("Could not find resource with name: '" + name + "'");
    }

    public ResourceConfiguration getResourceByType(Class<?> resourceType) {
        if (typeResourceMap.containsKey(resourceType)) {
            return typeResourceMap.get(resourceType);
        }
        throw new NoSuchResourceException("Could not find resource for type: '" + resourceType.getName() + "'");
    }

    public ResourceConfiguration getResourceForRequest(HttpServletRequest request) {
        return getResourceByUri(request.getRequestURI());
    }
}
