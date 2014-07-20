package ch.alv.components.web.api.config;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link ResourceConfiguration} class.
 *
 * @since 1.0.0
 */
public class ResourceConfigurationTest {

    private static final String NAME = "testResourceName";
    private static final String URI = "/testResource/{testParam1}/{testParam2}";
    private static final String URL = "http://testHost:8888";
    private static final Class<?> TYPE = ResourceConfigurationTest.class;
    private static final ResourceConfiguration PARENT = new ResourceConfiguration();
    private List<QueryParameter> QUERY_PARAMS = new ArrayList<>();
    private List<UriParameter> URI_PARAMS = new ArrayList<>();

    ResourceConfiguration configuration = new ResourceConfiguration();

    @Before
    public void init() {
        QueryParameter queryParam = new QueryParameter();
        queryParam.setName("idTest");
        QUERY_PARAMS.add(queryParam);

        UriParameter uriParamOne = new UriParameter();
        uriParamOne.setName("uriParamOne");
        UriParameter uriParamTwo = new UriParameter();
        uriParamTwo.setName("uriParamTwo");
        UriParameter uriParamThree = new UriParameter();
        uriParamThree.setName("uriParamThree");
        URI_PARAMS.add(uriParamOne);
        URI_PARAMS.add(uriParamTwo);
        URI_PARAMS.add(uriParamThree);

        configuration.setName(NAME);
        configuration.setUri(URI);
        configuration.setUrl(URL);
        configuration.setResourceType(TYPE);
        configuration.setParentResource(PARENT);
        configuration.setQueryParameters(QUERY_PARAMS);
        configuration.setUriParameters(URI_PARAMS);
    }

    @Test
    public void testResourceName() {
        assertEquals(NAME, configuration.getName());
    }

    @Test
    public void testUri() {
        assertEquals(URI, configuration.getUri());
    }

    @Test
         public void testUrl() {
        assertEquals(URL, configuration.getUrl());
    }

    @Test
    public void testResourceType() {
        assertEquals(TYPE, configuration.getResourceType());
    }

    @Test
    public void testParentResource() {
        assertEquals(PARENT, configuration.getParentResource());
    }

    @Test
    public void testUriParameters() {
        assertEquals(URI_PARAMS, configuration.getUriParameters());
    }

    @Test
    public void testQueryParameters() {
        assertEquals(QUERY_PARAMS, configuration.getQueryParameters());
    }

    @Test
    public void testHasIdUriParam() {
        assertFalse(configuration.hasIdUriParam());
        ResourceConfiguration tmpConfig = new ResourceConfiguration();
        UriParameter uriParam = new UriParameter();
        uriParam.setName("testId");
        tmpConfig.getUriParameters().add(uriParam);
        assertTrue(tmpConfig.hasIdUriParam());
    }

    @Test
    public void testHasUriParams() {
        assertTrue(configuration.hasUriParams());
        ResourceConfiguration tmpConfig = new ResourceConfiguration();
        assertFalse(tmpConfig.hasUriParams());
    }

    @Test
    public void testUriParamNames() {
        List<String> names = configuration.getUriParamNames();
        assertEquals(3, names.size());
        assertTrue(names.contains("uriParamOne"));
        assertTrue(names.contains("uriParamTwo"));
        assertTrue(names.contains("uriParamThree"));
    }

    @Test
    public void testHasIdQueryParameter() {
        assertFalse(configuration.hasIdQueryParameter());
        QueryParameter param = new QueryParameter();
        param.setName("itemId");
        ResourceConfiguration tmpConfig = new ResourceConfiguration();
        tmpConfig.getQueryParameters().add(param);
        assertTrue(tmpConfig.hasIdQueryParameter());
    }

    @Test
    public void testDescription() {
        ResourceConfiguration config = new ResourceConfiguration();
        config.setDescription("testDescription");
        assertEquals("testDescription", config.getDescription());
    }

    @Test
    public void testRelativeUri() {
        ResourceConfiguration config = new ResourceConfiguration();
        config.setRelativeUri("testRelUri");
        assertEquals("testRelUri", config.getRelativeUri());
    }

    @Test
    public void testParentUri() {
        ResourceConfiguration config = new ResourceConfiguration();
        config.setParentUri("testParentUri");
        assertEquals("testParentUri", config.getParentUri());
    }

    @Test
    public void testActions() {
        ResourceConfiguration config = new ResourceConfiguration();
        List<ActionConfiguration> actions = new ArrayList<>();
        config.setActions(actions);
        assertEquals(actions, config.getActions());
    }

    @Test
    public void testChildren() {
        ResourceConfiguration config = new ResourceConfiguration();
        List<ResourceConfiguration> kids = new ArrayList<>();
        config.setChildren(kids);
        assertEquals(kids, config.getChildren());
    }

    @Test
    public void testBaseUriParams() {
        ResourceConfiguration config = new ResourceConfiguration();
        List<UriParameter> params = new ArrayList<>();
        config.setBaseUriParameters(params);
        assertEquals(params, config.getBaseUriParameters());
    }

    @Test
    public void testResolvedUriParams() {
        ResourceConfiguration config = new ResourceConfiguration();
        List<UriParameter> params = new ArrayList<>();
        config.setResolvedUriParameters(params);
        assertEquals(params, config.getResolvedUriParameters());
    }

    @Test
    public void testSecurityConfigurations() {
        ResourceConfiguration config = new ResourceConfiguration();
        List<SecurityConfiguration> params = new ArrayList<>();
        config.setSecurityConfigurations(params);
        assertEquals(params, config.getSecurityConfigurations());
    }

}
