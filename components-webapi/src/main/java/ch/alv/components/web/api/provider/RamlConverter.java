package ch.alv.components.web.api.provider;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.web.api.config.*;
import ch.alv.components.web.api.http.HttpProtocol;
import org.raml.model.*;
import org.raml.model.ActionType;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.AbstractParam;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Converts a raml into a internal configuration representation.
 *
 * @since 1.0.0
 */
public class RamlConverter {

    public ApiConfiguration convertRamlToInternalConfiguration(Raml raml) {
        ApiConfiguration configuration = new ApiConfiguration();
        configuration.setTitle(convertTitle(raml.getTitle()));
        configuration.setVersion(convertVersion(raml.getVersion()));
        configuration.setBaseUri(convertBaseUri(raml.getBaseUri()));
        configuration.setBasePath(convertBasePath(raml.getBasePath()));
        configuration.setUri(convertUri(raml.getUri()));
        configuration.setProtocols(convertProtocols(raml.getProtocols()));
        configuration.setBaseUriParameters(convertUriParameterMap(raml.getBaseUriParameters()));
        configuration.setSecurityConfigurations(convertSecurityConstraints(raml.getSecuredBy()));
        configuration.setSchemas(convertSchemas(raml.getConsolidatedSchemas()));
        configuration.setMediaType(convertMediaType(raml.getMediaType()));
        configuration.setResourceTypes(convertResourceTypes(raml.getResourceTypes()));
        configuration.setQueryParameterCollections(convertTraitsToQueryParamCollections(raml.getTraits()));
        configuration.setResources(convertResources(raml.getResources()));
        return configuration;
    }

    public String convertTitle(String title) {
        return title;
    }

    public String convertName(String name) {
        return name;
    }

    public String convertDescription(String description) {
        return description;
    }

    public String convertVersion(String version) {
        return version;
    }

    public String convertBaseUri(String baseUri) {
        return baseUri;
    }

    public String convertBasePath(String basePath) {
        return basePath;
    }

    public String convertUri(String uri) {
        return uri;
    }

    public String convertPattern(String pattern) {
        return pattern;
    }

    public List<HttpProtocol> convertProtocols(List<Protocol> protocols) {
        List<HttpProtocol> list = new ArrayList<>();
        for (Protocol protocol : protocols) {
            list.add(convertProtocol(protocol));
        }
        return list;
    }

    public HttpProtocol convertProtocol(Protocol protocol) {
        if (protocol == Protocol.HTTP) {
            return HttpProtocol.HTTP;
        } else {
            return HttpProtocol.HTTPS;
        }
    }

    public List<ch.alv.components.web.api.config.UriParameter> convertUriParameterMap(Map<String, UriParameter> uriParameters) {
        List<ch.alv.components.web.api.config.UriParameter> list = new ArrayList<>();
        for (String key : uriParameters.keySet()) {
            list.add(convertUriParameter(uriParameters.get(key)));
        }
        return list;
    }

    public List<ch.alv.components.web.api.config.UriParameter> convertUriParameterListMap(Map<String, List<UriParameter>> baseUriParameters) {
        List<ch.alv.components.web.api.config.UriParameter> list = new ArrayList<>();
        if (baseUriParameters == null || baseUriParameters.isEmpty()) {
            return list;
        }
        for (String key : baseUriParameters.keySet()) {
            List<UriParameter> sources = baseUriParameters.get(key);
            for (UriParameter source : sources) {
                list.add(convertUriParameter(source));
            }
        }
        return list;
    }

    public ch.alv.components.web.api.config.UriParameter convertUriParameter(UriParameter source) {
        return (ch.alv.components.web.api.config.UriParameter) mapBaseParameterValues(source, new ch.alv.components.web.api.config.UriParameter());
    }

    public ch.alv.components.web.api.config.QueryParameter convertQueryParameter(QueryParameter source) {
        return (ch.alv.components.web.api.config.QueryParameter) mapBaseParameterValues(source, new ch.alv.components.web.api.config.QueryParameter());
    }

    public List<ch.alv.components.web.api.config.QueryParameter> convertQueryParameters(List<QueryParameter> source) {
        List<ch.alv.components.web.api.config.QueryParameter> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(source)) {
            return list;
        }
        for (QueryParameter parameter : source) {
            list.add(convertQueryParameter(parameter));
        }
        return list;
    }

    public List<ch.alv.components.web.api.config.QueryParameter> convertQueryParameters(Map<String, QueryParameter> source) {
        List<ch.alv.components.web.api.config.QueryParameter> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(source)) {
            return list;
        }
        for (String key : source.keySet()) {
            list.add(convertQueryParameter(source.get(key)));
        }
        return list;
    }

    public BaseParameter mapBaseParameterValues(AbstractParam source, BaseParameter target) {
        target.setName(convertName(source.getDisplayName()));
        target.setDescription(convertDescription(source.getDescription()));
        target.setType(convertParameterType(source.getType()));
        target.setPattern(convertPattern(source.getPattern()));
        return target;
    }

    public ParameterType convertParameterType(ParamType type) {
        if (type == null) {
            return null;
        }
        return ParameterType.valueOf(type.name());
    }

    public Map<String, ResourceType> convertResourceTypes(List<Map<String, Template>> resourceTypes) {
        Map<String, ResourceType> map = new HashMap<>();
        if (CollectionUtils.isEmpty(resourceTypes)) {
            return map;
        }
        for (Map<String, Template> sourceMap : resourceTypes) {
            if (sourceMap.size() == 0) {
                continue;
            }
            for (String key : sourceMap.keySet()) {
                map.put(key, convertResourceType(sourceMap.get(key)));
            }
        }
        return map;
    }

    public ResourceType convertResourceType(Template resourceType) {
        return ResourceType.byName(resourceType.getDisplayName().toUpperCase());
    }

    public ResourceType convertResourceType(String resourceType) {
        if (StringHelper.isEmpty(resourceType)) {
            return null;
        }
        return ResourceType.byName(resourceType);
    }

    public List<SecurityConfiguration> convertSecurityConstraints(List<SecurityReference> securedBy) {
        List<SecurityConfiguration> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(securedBy)) {
            return list;
        }
        for (SecurityReference reference : securedBy) {
            list.add(convertSecurityReference(reference));
        }
        return list;
    }

    public SecurityConfiguration convertSecurityReference(SecurityReference securityReference) {
        SecurityConfiguration config = new SecurityConfiguration();
        config.setName(securityReference.getName());
        config.setParameters(securityReference.getParameters());
        return config;
    }

    public Map<String, String> convertSchemas(Map<String, String> consolidatedSchemas) {
        return consolidatedSchemas;
    }

    public String convertMediaType(String mediaType) {
        return mediaType;
    }

    public Collection<QueryParameterCollection> convertTraitsToQueryParamCollections(List<Map<String, Template>> traits) {
        List<QueryParameterCollection> list = new ArrayList<>();
        if (traits.isEmpty()) {
            return list;
        }
        for (Map<String, Template> trait : traits) {
            list.add(convertTrait(trait));
        }
        return list;
    }

    public QueryParameterCollection convertTrait(Map<String, Template> trait) {
        QueryParameterCollection collection = new QueryParameterCollection();
        for (String key : trait.keySet()) {
            collection.getParameters().add(convertTemplateToQueryParameter(trait.get(key)));
        }
        return collection;
    }

    public ch.alv.components.web.api.config.QueryParameter convertTemplateToQueryParameter(Template source) {
        ch.alv.components.web.api.config.QueryParameter param = new ch.alv.components.web.api.config.QueryParameter();
        param.setName(convertName(source.getDisplayName()));
        return param;
    }

    public Collection<ResourceConfiguration> convertResources(Map<String, Resource> resources) {
        List<ResourceConfiguration> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(resources)) {
            return list;
        }
        for (String key : resources.keySet()) {
            list.add(convertResource(resources.get(key)));
        }
        return list;
    }

    public ResourceConfiguration convertResource(Resource resource) {
        if (resource == null) {
            return null;
        }
        ResourceConfiguration configuration = new ResourceConfiguration();
        mapResourceValuesToConfiguration(resource, configuration);

        for (String key : resource.getResources().keySet()) {
            Resource kid = resource.getResources().get(key);
            convertResourceToConfiguration(kid, configuration);
        }
        return configuration;
    }

    public void mapResourceValuesToConfiguration(Resource source, ResourceConfiguration target) {
        target.setUri(convertUri(source.getUri()));
        target.setUriParameters(convertUriParameterMap(source.getUriParameters()));
        target.setBaseUriParameters(convertUriParameterListMap(source.getBaseUriParameters()));
        target.setSecurityConfigurations(convertSecurityConstraints(source.getSecuredBy()));
        List<ActionConfiguration> actions = convertActions(source.getActions());
        for (ActionConfiguration action : actions) {
            action.setResource(target);
        }
        target.setActions(actions);
        ResourceType type = convertResourceType(source.getType());
        if (type != null) {
            target.setResourceType(type.getJavaClass());
        }
        target.setDescription(convertDescription(source.getDescription()));
        target.setName(convertName(source.getDisplayName()));
        convertAndAddAppliedTraits(source.getIs(), target.getQueryParameters());
    }

    public List<ActionConfiguration> convertActions(Map<org.raml.model.ActionType, Action> actions) {
        List<ActionConfiguration> list = new ArrayList<>();
        if (actions == null || actions.isEmpty()) {
            return list;
        }
        for (org.raml.model.ActionType key : actions.keySet()) {
            list.add(convertAction(actions.get(key)));
        }
        return list;
    }

    public ActionConfiguration convertAction(Action source) {
        ActionConfiguration target = new ActionConfiguration();
        target.setQueryParameters(convertQueryParameters(source.getQueryParameters()));
        convertAndAddAppliedTraits(source.getIs(), target.getQueryParameters());
        target.setSecurityConfigurations(convertSecurityConstraints(source.getSecuredBy()));
        target.setBaseUriParameters(convertUriParameterListMap(source.getBaseUriParameters()));
        target.setBody(convertMimeTypes(source.getBody()));
        target.setDescription(convertDescription(source.getDescription()));
        target.setHeaders(convertHeaders(source.getHeaders()));
        target.setProtocols(convertProtocols(source.getProtocols()));
        //target.setResource(convertResource(source.getResource()));
        target.setResponses(convertResponses(source.getResponses()));
        target.setType(convertActionType(source.getType()));
        return target;
    }

    public ch.alv.components.web.api.config.ActionType convertActionType(ActionType type) {
        if (type == null) {
            return null;
        }
        return ch.alv.components.web.api.config.ActionType.valueOf(type.name());
    }

    public List<ch.alv.components.web.api.config.MimeType> convertMimeTypes(Map<String, MimeType> source) {
        List<ch.alv.components.web.api.config.MimeType> list = new ArrayList<>();
        if (source == null || source.isEmpty()) {
            return list;
        }
        for (String key : source.keySet()) {
            list.add(convertMimeType(source.get(key)));
        }
        return list;
    }

    public ch.alv.components.web.api.config.MimeType convertMimeType(MimeType source) {
        if (source == null) {
            return null;
        }
        ch.alv.components.web.api.config.MimeType target = new ch.alv.components.web.api.config.MimeType();
        target.setExample(convertExample(source.getExample()));
        target.setType(source.getType());
        target.setFormParameters(getFormParametersMapList(source.getFormParameters()));
        target.setSchema(convertSchema(source.getSchema()));
        return target;
    }

    public String convertExample(String source) {
        return source;
    }

    public Map<String, List<FormParameter>> getFormParametersMapList(Map<String, List<org.raml.model.parameter.FormParameter>> source) {
        Map<String, List<FormParameter>> map = new HashMap<>();
        if (source == null) {
            return map;
        }
        for (String key : source.keySet()) {
            map.put(key, convertFormParameters(source.get(key)));
        }
        return map;
    }

    public List<FormParameter> convertFormParameters(List<org.raml.model.parameter.FormParameter> source) {
        List<FormParameter> list = new ArrayList<>();
        if (source.isEmpty()) {
            return list;
        }
        for (org.raml.model.parameter.FormParameter param : source) {
            list.add(convertFormParameter(param));
        }
        return list;
    }

    public FormParameter convertFormParameter(org.raml.model.parameter.FormParameter source) {
        if (source == null) {
            return null;
        }
        FormParameter target = new FormParameter();
        target.setExample(convertExample(source.getExample()));
        target.setDescription(convertDescription(source.getDescription()));
        target.setType(convertParameterType(source.getType()));
        target.setRepeat(source.isRepeat());
        target.setRequired(source.isRequired());
        target.setDefaultValue(convertDefaultValue(source.getDefaultValue()));
        target.setEnumeration(source.getEnumeration());
        target.setMaximum(source.getMaximum());
        target.setMinimum(source.getMinimum());
        target.setMinLength(source.getMinLength());
        target.setMaxLength(source.getMaxLength());
        target.setName(convertName(source.getDisplayName()));
        target.setPattern(convertPattern(source.getPattern()));
        return target;
    }

    public String convertSchema(String schema) {
        return schema;
    }

    public List<HeaderParameter> convertHeaders(Map<String, Header> source) {
        List<HeaderParameter> list = new ArrayList<>();
        if (source.isEmpty()) {
            return list;
        }

        for (String key : source.keySet()) {
            list.add(convertHeader(source.get(key)));
        }
        return list;
    }

    public HeaderParameter convertHeader(Header source) {
        if (source == null) {
            return null;
        }
        HeaderParameter target = new HeaderParameter();
        target.setExample(convertExample(source.getExample()));
        target.setDescription(convertDescription(source.getDescription()));
        target.setName(convertName(source.getDisplayName()));
        target.setPattern(convertPattern(source.getPattern()));
        target.setDefaultValue(convertDefaultValue(source.getDefaultValue()));
        target.setMaximum(source.getMaximum());
        target.setMinimum(source.getMinimum());
        target.setEnumeration(source.getEnumeration());
        target.setMaxLength(source.getMaxLength());
        target.setMinLength(source.getMinLength());
        target.setRepeat(source.isRepeat());
        target.setRequired(source.isRequired());
        target.setType(convertParameterType(source.getType()));
        return target;
    }

    public String convertDefaultValue(String defaultValue) {
        return defaultValue;
    }

    public List<ch.alv.components.web.api.config.Response> convertResponses(Map<String, Response> source) {
        List<ch.alv.components.web.api.config.Response> list = new ArrayList<>();
        if (source.isEmpty()) {
            return list;
        }
        for (String key : source.keySet()) {
            list.add(convertResponse(source.get(key)));
        }
        return list;
    }

    public ch.alv.components.web.api.config.Response convertResponse(Response source) {
        if (source == null) {
            return null;
        }
        ch.alv.components.web.api.config.Response target = new ch.alv.components.web.api.config.Response();
        target.setDescription(convertDescription(source.getDescription()));
        target.setBody(convertMimeTypes(source.getBody()));
        target.setHeaders(convertHeaders(source.getHeaders()));
        return target;
    }

    public void convertAndAddAppliedTraits(List<String> is, List<ch.alv.components.web.api.config.QueryParameter> queryParameters) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public ResourceConfiguration convertResourceToConfiguration(Resource resource, ResourceConfiguration parent) {
        if (resource == null) {
            return null;
        }
        ResourceConfiguration configuration = new ResourceConfiguration();
        mapResourceValuesToConfiguration(resource, configuration);
        if (parent != null) {
            parent.getChildren().add(configuration);
            configuration.setParentResource(parent);
        }

        for (String key : resource.getResources().keySet()) {
            Resource kid = resource.getResources().get(key);
            convertResourceToConfiguration(kid, configuration);
        }
        return configuration;
    }

}
