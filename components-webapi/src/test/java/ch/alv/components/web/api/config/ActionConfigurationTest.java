package ch.alv.components.web.api.config;

import ch.alv.components.web.api.http.HttpProtocol;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.ActionConfiguration} class.
 *
 * @since 1.0.0
 */
public class ActionConfigurationTest {

    private ActionConfiguration configuration = new ActionConfiguration();

    @Test
    public void testType() {
        configuration.setType(ActionType.GET);
        assertEquals(ActionType.GET, configuration.getType());
    }

    @Test
    public void testQueryParameters() {
        List<QueryParameter> list = new ArrayList<>();
        configuration.setQueryParameters(list);
        assertTrue(list == configuration.getQueryParameters());
    }

    @Test
    public void testSecurityConfigurations() {
        List<SecurityConfiguration> list = new ArrayList<>();
        configuration.setSecurityConfigurations(list);
        assertTrue(list == configuration.getSecurityConfigurations());
    }

    @Test
    public void testBaseParameters() {
        List<UriParameter> list = new ArrayList<>();
        configuration.setBaseUriParameters(list);
        assertTrue(list == configuration.getBaseUriParameters());
    }

    @Test
    public void testBody() {
        List<MimeType> list = new ArrayList<>();
        configuration.setBody(list);
        assertTrue(list == configuration.getBody());
    }

    @Test
    public void testDescription() {
        configuration.setDescription("testDescription");
        assertEquals("testDescription", configuration.getDescription());
    }

    @Test
    public void testHeaders() {
        List<HeaderParameter> list = new ArrayList<>();
        configuration.setHeaders(list);
        assertTrue(list == configuration.getHeaders());
    }

    @Test
    public void testProtocols() {
        List<HttpProtocol> list = new ArrayList<>();
        configuration.setProtocols(list);
        assertTrue(list == configuration.getProtocols());
    }

    @Test
    public void testResource() {
        ResourceConfiguration config = new ResourceConfiguration();
        configuration.setResource(config);
        assertTrue(config == configuration.getResource());
    }

    @Test
    public void testResponses() {
        List<Response> list = new ArrayList<>();
        configuration.setResponses(list);
        assertTrue(list == configuration.getResponses());
    }

}
