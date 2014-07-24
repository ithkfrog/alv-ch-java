package ch.alv.components.web.api.provider;

import ch.alv.components.web.api.config.*;
import ch.alv.components.web.api.http.HttpProtocol;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.raml.model.*;
import org.raml.model.ActionType;
import org.raml.model.MimeType;
import org.raml.model.Response;
import org.raml.model.parameter.FormParameter;
import org.raml.model.parameter.Header;
import org.raml.model.parameter.QueryParameter;
import org.raml.model.parameter.UriParameter;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link RamlConverter} class.
 *
 * @since 1.0.0
 */
public class RamlConverterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private RamlConverter converter = new RamlConverter();

    private Raml raml = new Raml();
    private Action action = new Action();
    private UriParameter uriParameter = new UriParameter();
    private List<UriParameter> uriParameterList = new ArrayList<>();
    private Map<String, List<UriParameter>> baseUriParams = new HashMap<>();
    private Map<String, UriParameter> uriParamsMap = new HashMap<>();
    private Map<String, MimeType> body = new HashMap<>();
    private MimeType mimeType = new MimeType();
    private Header header = new Header();
    private Map<String, Header> headers = new HashMap<>();
    private List<String> is = new ArrayList<>();
    private List<Protocol> protocols = new ArrayList<>();
    private Map<String, QueryParameter> queryParamsMap = new HashMap<>();
    private QueryParameter queryParameter = new QueryParameter();
    private List<QueryParameter> queryParameterList = new ArrayList<>();
    private Resource actionResource = new Resource();
    private Map<String, Response> responses = new HashMap<>();
    private Response response = new Response();
    private List<SecurityReference> securedBy = new ArrayList<>();
    private SecurityReference reference = new SecurityReference("testSecurityReference");
    private List<Map<String, Template>> resourceTypes = new ArrayList<>();
    private Map<String, Template> resourceType = new HashMap<>();
    private Template template = new Template();
    private Map<String, String> schemas = new HashMap<>();
    private Resource resource = new Resource();
    private Map<String, Resource> resources = new HashMap<>();

    @Before
    public void init() {
        raml.setTitle("testTitle");
        raml.setVersion("testVersion");
        raml.setBaseUri("http://localhost:80/baseUri");
        raml.setProtocols(new ArrayList<Protocol>());
        raml.setBaseUriParameters(uriParamsMap);
        raml.setSecuredBy(securedBy);
        raml.setSchemas(new ArrayList<Map<String, String>>());
        raml.setMediaType("testMediaType");
        raml.setResourceTypes(resourceTypes);
        raml.setTraits(resourceTypes);
        raml.setResources(resources);

        action.setDescription("testDescription");
        action.setType(ActionType.GET);
        action.setBaseUriParameters(baseUriParams);
        action.setBody(body);
        action.setHeaders(headers);
        action.setIs(is);
        action.setProtocols(protocols);
        action.setQueryParameters(queryParamsMap);
        action.setResource(actionResource);
        action.setResponses(responses);
        action.setSecuredBy(securedBy);

        uriParameter.setDisplayName("uriParam");
        uriParameter.setDescription("uriParamDescription");
        uriParameter.setType(ParamType.BOOLEAN);
        uriParameter.setPattern("uriParamPattern");

        uriParamsMap.put("uriParam", uriParameter);
        uriParameterList.add(uriParameter);
        baseUriParams.put("testBaseUriParams", uriParameterList);

        mimeType.setType("integer");

        body.put("testMimeType", mimeType);

        header.setDisplayName("testHeader");

        headers.put("testHeaders", header);

        is.add("testIs");

        protocols.add(Protocol.HTTP);

        queryParamsMap.put("testQueryParameter", queryParameter);

        queryParameter.setDisplayName("testQueryParameter");

        queryParameterList.add(queryParameter);

        actionResource.setDisplayName("testResource");

        responses.put("testResponse", response);

        securedBy.add(reference);

        template.setDisplayName("NUMBER");

        resourceType.put(template.getDisplayName(), template);

        resourceTypes.add(resourceType);

        resource.setDisplayName("testResource");
        resource.setType("boolean");
        resources.put(resource.getDisplayName(), resource);

        schemas.put("testSchema", "{ id: number}");

    }

    @Test
    public void convertRamlToInternalConfiguration() {
        ApiConfiguration result = converter.convertRamlToInternalConfiguration(raml);
        assertEquals(raml.getTitle(), result.getTitle());

        assertEquals(raml.getVersion(), result.getVersion());
        assertEquals(raml.getBaseUri(), result.getBaseUri());
        assertEquals(raml.getBasePath(), result.getBasePath());
        assertEquals(raml.getProtocols().size(), result.getProtocols().size());
        assertEquals(raml.getBaseUriParameters().size(), result.getBaseUriParameters().size());
        assertEquals(raml.getSecuredBy().size(), result.getSecurityConfigurations().size());
        assertEquals(raml.getSchemas().size(), result.getSchemas().size());
        assertEquals(raml.getMediaType(), result.getMediaType());
        assertEquals(raml.getResourceTypes().size(), result.getResourceTypes().size());
        assertEquals(raml.getTraits().size(), result.getQueryParameterCollections().size());
        assertEquals(raml.getResources().size(), result.getResources().size());
    }

    @Test
    public void testConvertTitle() {
        assertEquals("testTitle", converter.convertTitle("testTitle"));
    }

    @Test
    public void testConvertName() {
        assertEquals("testName", converter.convertTitle("testName"));
    }

    @Test
    public void testConvertVersion() {
        assertEquals("testVersion", converter.convertVersion("testVersion"));
    }

    @Test
    public void testConvertBaseUri() {
        assertEquals("testBaseUri", converter.convertBaseUri("testBaseUri"));
    }

    @Test
    public void testConvertBasePath() {
        assertEquals("testBasePath", converter.convertBasePath("testBasePath"));
    }

    @Test
    public void testConvertUri() {
        assertEquals("testUri", converter.convertUri("testUri"));
    }

    @Test
    public void testConvertPattern() {
        assertEquals("testPattern", converter.convertPattern("testPattern"));
    }

    @Test
    public void testConvertProtocols() {
        List<Protocol> protocols = new ArrayList<>();
        protocols.add(Protocol.HTTP);
        protocols.add(Protocol.HTTPS);
        List<HttpProtocol> result = converter.convertProtocols(protocols);
        assertEquals(2, result.size());
        assertTrue(result.contains(HttpProtocol.HTTP));
        assertTrue(result.contains(HttpProtocol.HTTPS));
    }

    @Test
    public void testConvertProtocol() {
        assertEquals(HttpProtocol.HTTP, converter.convertProtocol(Protocol.HTTP));
        assertEquals(HttpProtocol.HTTPS, converter.convertProtocol(Protocol.HTTPS));
        assertEquals(2, HttpProtocol.values().length);
    }

    @Test
    public void testConvertUriParameterMap() {
        List<ch.alv.components.web.api.config.UriParameter> result = converter.convertUriParameterMap(uriParamsMap);
        assertEquals(1, result.size());
        assertEquals(uriParameter.getDisplayName(), result.get(0).getName());
    }

    @Test
    public void testConvertUriParameterListMap() {
        assertEquals(0, converter.convertUriParameterListMap(null).size());
        assertEquals(0, converter.convertUriParameterListMap(new HashMap<String, List<UriParameter>>()).size());
    }

    @Test
    public void testConvertQueryParametersList() {
        List<ch.alv.components.web.api.config.QueryParameter> result = converter.convertQueryParameters(queryParamsMap);
        assertEquals(1, result.size());
        assertEquals(queryParameter.getDisplayName(), result.get(0).getName());
        assertEquals(0, converter.convertQueryParameters(new ArrayList<QueryParameter>()).size());
    }

    @Test
    public void testConvertQueryParametersMap() {
        List<ch.alv.components.web.api.config.QueryParameter> result = converter.convertQueryParameters(queryParameterList);
        assertEquals(1, result.size());
        assertEquals(queryParameter.getDisplayName(), result.get(0).getName());
        assertEquals(0, converter.convertQueryParameters(new HashMap<String, QueryParameter>()).size());
    }

    @Test
    public void testMapParameterValues() {
        ch.alv.components.web.api.config.UriParameter target = new ch.alv.components.web.api.config.UriParameter();
        converter.mapBaseParameterValues(uriParameter, target);
        assertEquals(uriParameter.getDisplayName(), target.getName());
        assertEquals(uriParameter.getDescription(), target.getDescription());
        assertEquals(uriParameter.getPattern(), target.getPattern());
        assertEquals(ParameterType.BOOLEAN, target.getType());
    }

    @Test
    public void testConvertParameterType() {
        assertEquals(ParameterType.STRING, converter.convertParameterType(ParamType.STRING));
        assertEquals(ParameterType.BOOLEAN, converter.convertParameterType(ParamType.BOOLEAN));
        assertEquals(ParameterType.NUMBER, converter.convertParameterType(ParamType.NUMBER));
        assertEquals(ParameterType.INTEGER, converter.convertParameterType(ParamType.INTEGER));
        assertEquals(ParameterType.DATE, converter.convertParameterType(ParamType.DATE));
        assertEquals(ParameterType.FILE, converter.convertParameterType(ParamType.FILE));
        assertEquals(6, ParameterType.values().length);
        assertNull(converter.convertParameterType(null));
    }

    @Test
    public void testConvertResourceTypes() {
        Map<String, ResourceType> map = converter.convertResourceTypes(resourceTypes);
        assertEquals(1, map.size());
        assertEquals(template.getDisplayName(), map.get(template.getDisplayName()).name());
        assertEquals(0, converter.convertResourceTypes(null).size());
        List<Map<String, Template>> sourceList = new ArrayList<>();
        sourceList.add(new HashMap<String, Template>());
        assertEquals(0, converter.convertResourceTypes(sourceList).size());
    }

    @Test
    public void testConvertTemplateToResourceType() {
        assertEquals(ResourceType.NUMBER, converter.convertResourceType(template));
        Template failingTemplate = new Template();
        failingTemplate.setDisplayName("unknownType");
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("No enum constant ch.alv.components.web.api.config.ResourceType.UNKNOWNTYPE");
        converter.convertResourceType(failingTemplate);
    }

    @Test
    public void testConvertSecurityReferences() {
        List<SecurityConfiguration> result = converter.convertSecurityConstraints(securedBy);
        assertEquals(1, result.size());
        assertEquals(reference.getName(), result.get(0).getName());
    }

    @Test
    public void testConvertSecurityReference() {
        SecurityConfiguration result = converter.convertSecurityReference(reference);
        assertEquals(reference.getName(), result.getName());
        assertEquals(reference.getParameters().size(), result.getParameters().size());
    }

    @Test
    public void testConvertSchemas() {
        Map<String, String> result = converter.convertSchemas(schemas);
        assertEquals(result.keySet(), schemas.keySet());
        assertEquals(result.values(), schemas.values());
    }

    @Test
    public void testConvertMediaTypes() {
        String mediaType = "testMediaType";
        assertEquals(mediaType, converter.convertMediaType(mediaType));
    }

    @Test
    public void testConvertTraitsToQueryParamCollections() {
        Collection<QueryParameterCollection> result = converter.convertTraitsToQueryParamCollections(resourceTypes);
        assertEquals(resourceTypes.size(), result.size());
        assertEquals(0, converter.convertTraitsToQueryParamCollections(new ArrayList<Map<String, Template>>()).size());
    }

    @Test
    public void testConvertTrait() {
        QueryParameterCollection result = converter.convertTrait(resourceType);
        assertEquals(resourceType.size(), result.getParameters().size());
    }

    @Test
    public void testConvertTemplateToQueryParameter() {
        ch.alv.components.web.api.config.QueryParameter result = converter.convertTemplateToQueryParameter(template);
        assertEquals(template.getDisplayName(), result.getName());
    }

    @Test
    public void testConvertResources() {
        List<ResourceConfiguration> result = (List<ResourceConfiguration>) converter.convertResources(resources);
        assertEquals(1, result.size());
        assertEquals(resource.getDisplayName(), result.get(0).getName());
        assertEquals(0, converter.convertResources(new HashMap<String, Resource>()).size());
    }

    @Test
    public void testConvertResource() {
        Resource kid = new Resource();
        kid.setDisplayName("kidResource");
        resource.getResources().put(kid.getDisplayName(), kid);
        ResourceConfiguration result = converter.convertResource(resource);
        assertEquals(resource.getDisplayName(), result.getName());
        assertEquals(1, result.getChildren().size());
        assertNull(converter.convertResource(null));
    }

    @Test
    public void testConvertActions() {
        Map<ActionType, Action> actions = new HashMap<>();
        actions.put(ActionType.GET, action);
        List<ActionConfiguration> result = converter.convertActions(actions, new ResourceConfiguration());
        assertEquals(1, result.size());
        assertEquals(0, converter.convertActions(new HashMap<ActionType, Action>(), new ResourceConfiguration()).size());
        assertEquals(0, converter.convertActions(null, new ResourceConfiguration()).size());

    }

    @Test
    public void testConvertAction() {
        ActionConfiguration target = converter.convertAction(action, new ResourceConfiguration());
        assertEquals("testDescription", target.getDescription());
        assertEquals(ch.alv.components.web.api.config.ActionType.GET, target.getType());
    }

    @Test
    public void testConvertActionType() {
        assertEquals(ch.alv.components.web.api.config.ActionType.GET, converter.convertActionType(ActionType.GET));
        assertEquals(ch.alv.components.web.api.config.ActionType.POST, converter.convertActionType(ActionType.POST));
        assertEquals(ch.alv.components.web.api.config.ActionType.PUT, converter.convertActionType(ActionType.PUT));
        assertEquals(ch.alv.components.web.api.config.ActionType.DELETE, converter.convertActionType(ActionType.DELETE));
        assertEquals(ch.alv.components.web.api.config.ActionType.TRACE, converter.convertActionType(ActionType.TRACE));
        assertEquals(ch.alv.components.web.api.config.ActionType.OPTIONS, converter.convertActionType(ActionType.OPTIONS));
        assertEquals(ch.alv.components.web.api.config.ActionType.PATCH, converter.convertActionType(ActionType.PATCH));
        assertEquals(ch.alv.components.web.api.config.ActionType.HEAD, converter.convertActionType(ActionType.HEAD));
        assertEquals(8, ch.alv.components.web.api.config.ActionType.values().length);
        assertNull(converter.convertActionType(null));
    }

    @Test
    public void testConvertMimeTypes() {
        Map<String, MimeType> sourceTypes = new HashMap<>();
        MimeType sourceType = new MimeType();
        sourceType.setType("number");
        sourceTypes.put("testMimeType", sourceType);
        List<ch.alv.components.web.api.config.MimeType> result = converter.convertMimeTypes(sourceTypes);
        assertEquals(1, result.size());
        assertEquals("number", result.get(0).getType());

        assertEquals(0, converter.convertMimeTypes(null).size());
        assertEquals(0, converter.convertMimeTypes(new HashMap<String, MimeType>()).size());
    }

    @Test
    public void testConvertMimeType() {
        mimeType.setExample("mimeTypeExample");
        mimeType.setSchema("mimeTypeSchema");
        mimeType.setType("String");

        List<FormParameter> formParameters = new ArrayList<>();
        FormParameter formParam = new FormParameter();
        formParam.setDisplayName("testFormParam");
        Map<String, List<FormParameter>> formParameterMap = new HashMap<>();
        formParameterMap.put("testFormParameterMap", formParameters);
        mimeType.setFormParameters(formParameterMap);
        ch.alv.components.web.api.config.MimeType result = converter.convertMimeType(mimeType);

        assertEquals(mimeType.getExample(), result.getExample());
        assertEquals(mimeType.getFormParameters().size(), result.getFormParameters().size());
        assertEquals(mimeType.getSchema(), result.getSchema());
        assertEquals("String", result.getType());

        assertNull(converter.convertMimeType(null));
    }

    @Test
    public void testConvertExample() {
        assertEquals("testExampleToConvert", converter.convertExample("testExampleToConvert"));
    }

    @Test
    public void testConvertFormParameters() {
        List<FormParameter> sourceList = new ArrayList<>();
        sourceList.add(new FormParameter());
        List<ch.alv.components.web.api.config.FormParameter> result = converter.convertFormParameters(sourceList);
        assertEquals(1, result.size());
        assertNotNull(result.get(0));
    }

    @Test
    public void testConvertFormParameter() {
        FormParameter source = new FormParameter();
        source.setDisplayName("testFormParam");
        source.setExample("testFormParamExample");
        source.setPattern("testFormParamPattern");
        source.setType(ParamType.BOOLEAN);
        source.setDefaultValue("testFormParamDefaultValue");
        List<String> enums = new ArrayList<>();
        enums.add("testValue");
        source.setEnumeration(enums);
        source.setMaximum(new BigDecimal("5"));
        source.setMinimum(new BigDecimal("1"));
        source.setMinLength(3);
        source.setMaxLength(9);
        source.setRepeat(true);
        source.setRequired(true);
        ch.alv.components.web.api.config.FormParameter result = converter.convertFormParameter(source);

        assertEquals(source.getDisplayName(), result.getName());
        assertEquals(source.getExample(), result.getExample());
        assertEquals(source.getPattern(), result.getPattern());
        assertEquals(ParameterType.BOOLEAN, result.getType());
        assertEquals(source.getDefaultValue(), result.getDefaultValue());
        assertEquals(source.getEnumeration(), result.getEnumeration());
        assertEquals(source.getMaximum(), result.getMaximum());
        assertEquals(source.getMinimum(), result.getMinimum());
        assertEquals(source.getMinLength(), result.getMinLength());
        assertEquals(source.getMaxLength(), result.getMaxLength());
        assertEquals(source.isRepeat(), result.isRepeat());
        assertEquals(source.isRequired(), result.isRequired());

        assertNull(converter.convertFormParameter(null));
    }

    @Test
    public void testConvertSchema() {
        String schema = "testSchema";
        assertEquals(schema, converter.convertSchema(schema));
    }

    @Test
    public void testConvertHeaders() {
        Map<String, Header> sourceHeaders = new HashMap<>();
        sourceHeaders.put("testHeader", new Header());
        List<HeaderParameter> result = converter.convertHeaders(sourceHeaders);
        assertEquals(1, result.size());
    }

    @Test
    public void testConvertHeader() {
        Header source = new Header();
        source.setDisplayName("testHeader");
        source.setExample("testHeaderExample");
        source.setPattern("testHeaderPattern");
        source.setType(ParamType.INTEGER);
        source.setDefaultValue("testHeaderDefaultValue");
        List<String> enums = new ArrayList<>();
        enums.add("testValue");
        source.setEnumeration(enums);
        source.setMaximum(new BigDecimal("5"));
        source.setMinimum(new BigDecimal("1"));
        source.setMinLength(3);
        source.setMaxLength(9);
        source.setRepeat(true);
        source.setRequired(true);
        HeaderParameter result = converter.convertHeader(source);

        assertEquals(source.getDisplayName(), result.getName());
        assertEquals(source.getExample(), result.getExample());
        assertEquals(source.getPattern(), result.getPattern());
        assertEquals(ParameterType.INTEGER, result.getType());
        assertEquals(source.getDefaultValue(), result.getDefaultValue());
        assertEquals(source.getEnumeration(), result.getEnumeration());
        assertEquals(source.getMaximum(), result.getMaximum());
        assertEquals(source.getMinimum(), result.getMinimum());
        assertEquals(source.getMinLength(), result.getMinLength());
        assertEquals(source.getMaxLength(), result.getMaxLength());
        assertEquals(source.isRepeat(), result.isRepeat());
        assertEquals(source.isRequired(), result.isRequired());

        assertNull(converter.convertHeader(null));
    }

    @Test
    public void testConvertDefaultValue() {
        assertEquals("testDefaultValue", converter.convertDefaultValue("testDefaultValue"));
    }

    @Test
    public void testConvertResponses() {
        Map<String, Response> source = new HashMap<>();
        source.put("testResponse", new Response());
        List<ch.alv.components.web.api.config.Response> result = converter.convertResponses(source);
        assertEquals(1, result.size());
        assertEquals(0, converter.convertResponses(new HashMap<String, Response>()).size());
    }

    @Test
    public void testConvertResponse() {
        Response source = new Response();
        source.setDescription("responseDescription");
        Map<String, Header> headers = new HashMap<>();
        headers.put("testHeader", new Header());
        Map<String, MimeType> body = new HashMap<>();
        body.put("testBody", new MimeType());
        source.setHeaders(headers);
        source.setBody(body);

        ch.alv.components.web.api.config.Response result = converter.convertResponse(source);
        assertEquals(source.getDescription(), result.getDescription());
        assertEquals(source.getBody().size(), result.getBody().size());
        assertEquals(source.getHeaders().size(), result.getHeaders().size());

        assertNull(converter.convertResponse(null));
    }

    @Test
    public void testConvertResourceTypeWithString() {
        assertEquals(ResourceType.STRING, converter.convertResourceType("string"));
        assertEquals(ResourceType.BOOLEAN, converter.convertResourceType("boolean"));
        assertEquals(ResourceType.NUMBER, converter.convertResourceType("nUmBeR"));
        assertEquals(ResourceType.INTEGER, converter.convertResourceType("integer"));
        assertEquals(ResourceType.DATE, converter.convertResourceType("DATE"));
        assertEquals(ResourceType.FILE, converter.convertResourceType("File"));
        assertEquals(ResourceType.OBJECT, converter.convertResourceType("object"));
        assertNull(converter.convertResourceType(""));
        assertEquals(7, ResourceType.values().length);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("No enum constant ch.alv.components.web.api.config.ResourceType.UNKNOWN");
        converter.convertResourceType("unknown");
    }

    @Test
    public void testConvertResourceTypeWithTemplate() {
        Template template = new Template();
        template.setDisplayName("string");
        assertEquals(ResourceType.STRING, converter.convertResourceType(template));
        template.setDisplayName("BooLean");
        assertEquals(ResourceType.BOOLEAN, converter.convertResourceType(template));
        template.setDisplayName("NUMBER");
        assertEquals(ResourceType.NUMBER, converter.convertResourceType(template));
        template.setDisplayName("iNTEGER");
        assertEquals(ResourceType.INTEGER, converter.convertResourceType(template));
        template.setDisplayName("Date");
        assertEquals(ResourceType.DATE, converter.convertResourceType(template));
        template.setDisplayName("file");
        assertEquals(ResourceType.FILE, converter.convertResourceType(template));
        template.setDisplayName("object");
        assertEquals(ResourceType.OBJECT, converter.convertResourceType(template));
        template.setDisplayName("");
        assertNull(converter.convertResourceType(""));
        template.setDisplayName("unknown");
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("No enum constant ch.alv.components.web.api.config.ResourceType.UNKNOWN");
        converter.convertResourceType("unknown");
    }

    @Test
    public void testConvertAndAddAppliedTraits() {
        // not implemented yet
    }

    @Test
    public void testConvertResourceToConfiguration() {
        assertNull(converter.convertResourceToConfiguration(null, null));

        Resource parent = new Resource();
        Resource kid = new Resource();
        parent.getResources().put("kidOne", kid);
        ResourceConfiguration result = converter.convertResourceToConfiguration(parent, null);
        assertEquals(parent.getResources().size(), result.getChildren().size());
    }

    @Test
    public void testReplaceProtocolWithRegex() {

    }

}
