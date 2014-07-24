package ch.alv.components.web.api.config;

import ch.alv.components.web.api.http.HttpProtocol;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ApiConfiguration} class.
 *
 * @since 1.0.0
 */
public class ApiConfigurationTest {

    private static final String TITLE = "testTitle";
    private static final String PORT = ":9999";
    private static final String PATH = "/api";
    private static final String BASE_URI = "http://localhost" + PORT + PATH;
    private static final String NAME_ONE = "one";
    private static final String NAME_TWO = "two";
    private static final String NAME_THREE = "three";
    private static final Class<?> RESOURCE_ONE = ApiConfiguration.class;
    private static final Class<?> RESOURCE_TWO = String.class;
    private static final Class<?> RESOURCE_THREE = ApiConfigurationTest.class;
    private static final String VERSION = "v0.1";
    private static final String MEDIA_TYPE = "testMediaType";
    private static final String URI = "testUri";
    private static final HttpProtocol[] PROTOCOLS = new HttpProtocol[]{HttpProtocol.HTTPS};


    private List<QueryParameterCollection> queryParameters = new ArrayList<>();

    private List<ResourceConfiguration> resources = new ArrayList<>();

    private MockHttpServletRequest request = new MockHttpServletRequest();

    private ApiConfiguration configuration;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        configuration = new ApiConfiguration();
        configuration.setTitle(TITLE);
        configuration.setBaseUri(BASE_URI);
        configuration.setVersion(VERSION);
        configuration.setMediaType(MEDIA_TYPE);
        configuration.setUri(URI);
        configuration.setBasePath(BASE_URI);
        configuration.setProtocols(Arrays.asList(PROTOCOLS));

        queryParameters.add(new QueryParameterCollection());
        queryParameters.add(new QueryParameterCollection());
        queryParameters.add(new QueryParameterCollection());
        configuration.setQueryParameterCollections(queryParameters);

        ResourceConfiguration one = new ResourceConfiguration();
        one.setUri("/" + NAME_ONE);
        one.setUrl(BASE_URI + "/" + NAME_ONE);
        one.setName(NAME_ONE);
        one.setResourceType(RESOURCE_ONE);

        ResourceConfiguration two = new ResourceConfiguration();
        two.setUri("/" + NAME_TWO);
        two.setUrl(BASE_URI + "/" + NAME_TWO);
        two.setName(NAME_TWO);
        two.setResourceType(RESOURCE_TWO);

        ResourceConfiguration three = new ResourceConfiguration();
        three.setUri("/" + NAME_THREE);
        three.setUrl(BASE_URI + "/" + NAME_THREE);
        three.setName(NAME_THREE);
        three.setResourceType(RESOURCE_THREE);

        resources.add(one);
        resources.add(two);
        resources.add(two);
        resources.add(three);
        configuration.setResources(resources);

    }

    @Test
    public void testTitle() {
        assertEquals(TITLE, configuration.getTitle());
    }

    @Test
    public void testMediaType() {
        assertEquals(MEDIA_TYPE, configuration.getMediaType());
    }

    @Test
    public void testBaseUri() {
        assertEquals(BASE_URI, configuration.getBaseUri());
    }

    @Test
    public void testUri() {
        assertEquals(URI, configuration.getUri());
    }

    @Test
    public void testBasePath() {
        assertEquals(BASE_URI, configuration.getBasePath());
    }

    @Test
    public void testVersion() {
        assertEquals(VERSION, configuration.getVersion());
    }

    @Test
    public void testProtocols() {
        assertEquals(Arrays.asList(PROTOCOLS), configuration.getProtocols());
    }

    @Test
    public void testBaseUriParameters() {
        List<UriParameter> params = new ArrayList<>();
        configuration.setBaseUriParameters(params);
        assertEquals(params, configuration.getBaseUriParameters());
    }

    @Test
    public void testSchemas() {
        Map<String, String> schemas = new HashMap<>();
        configuration.setSchemas(schemas);
        assertEquals(schemas, configuration.getSchemas());
    }

    @Test
    public void testQueryParameterCollections() {
        assertEquals(queryParameters.size(), configuration.getQueryParameterCollections().size());
    }

    @Test
    public void testResources() {
        // setting resources should strip double entries...
        assertEquals(resources.size() - 1, configuration.getResources().size());

        // null or an empty list should reset all related maps
        configuration.setResources(null);
        assertEquals(0, configuration.getResources().size());
        exception.expect(NoSuchResourceException.class);
        configuration.getResourceByName(NAME_ONE);

        exception.expect(NoSuchResourceException.class);
        configuration.getResourceByUri("/" + NAME_ONE);

        exception.expect(NoSuchResourceException.class);
        configuration.getResourceByType(RESOURCE_ONE);

        configuration.setResources(resources);
    }

    @Test
    public void testResourceTypes() {
        Map<String, ResourceType> types = new HashMap<>();
        configuration.setResourceTypes(types);
        assertEquals(types, configuration.getResourceTypes());
    }

    @Test
    public void testSecurityConfigurations() {
        List<SecurityConfiguration> conf = new ArrayList<>();
        configuration.setSecurityConfigurations(conf);
        assertEquals(conf, configuration.getSecurityConfigurations());
    }

    @Test
    public void testResourceByUri() {
        assertEquals(NAME_ONE, configuration.getResourceByUri("/" + NAME_ONE).getName());
        assertEquals(NAME_TWO, configuration.getResourceByUri("/" + NAME_TWO).getName());
        assertEquals(NAME_THREE, configuration.getResourceByUri("/" + NAME_THREE).getName());

        exception.expect(NoSuchResourceException.class);
        configuration.getResourceByUri("/");
    }

    @Test
    public void testResourceByName() {
        assertEquals(NAME_ONE, configuration.getResourceByName(NAME_ONE).getName());
        assertEquals(NAME_TWO, configuration.getResourceByName(NAME_TWO).getName());
        assertEquals(NAME_THREE, configuration.getResourceByName(NAME_THREE).getName());

        exception.expect(NoSuchResourceException.class);
        configuration.getResourceByName("/");
    }

    @Test
    public void testResourceByType() {
        assertEquals(NAME_ONE, configuration.getResourceByType(RESOURCE_ONE).getName());
        assertEquals(NAME_TWO, configuration.getResourceByType(RESOURCE_TWO).getName());
        assertEquals(NAME_THREE, configuration.getResourceByType(RESOURCE_THREE).getName());
        exception.expect(NoSuchResourceException.class);
        configuration.getResourceByType(Integer.class);
    }

    @Test
    public void testResourceForRequest() {
        request.setRequestURI("/api/" + NAME_ONE);
        request.setServerPort(9999);
        assertEquals(NAME_ONE, configuration.getResourceForRequest(request).getName());
        request.setRequestURI("/api/" + NAME_TWO);
        assertEquals(NAME_TWO, configuration.getResourceForRequest(request).getName());
        request.setRequestURI("/api/" + NAME_THREE);
        assertEquals(NAME_THREE, configuration.getResourceForRequest(request).getName());

        exception.expect(NoSuchResourceException.class);
        request.setRequestURI("/");
        configuration.getResourceByUri("/");
    }
}
