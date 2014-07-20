package ch.alv.components.web.api.handler;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.config.ResourceConfiguration;
import ch.alv.components.web.api.mock.MockResource;
import ch.alv.components.web.api.mock.MockTargetedResource;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link DefaultPutRequestHandler} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-request-handler-test-context.xml")
public class DefaultPutRequestHandlerTest {

    private MockHttpServletRequest request = new MockHttpServletRequest();

    private ApiConfiguration apiConfiguration = new ApiConfiguration();

    private HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request) ;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private PutRequestHandler handler;

    @Before
    public void init() {
        apiConfiguration = new ApiConfiguration();
        apiConfiguration.setBaseUri("http://localhost");

        ResourceConfiguration resourceConfiguration = new ResourceConfiguration();
        resourceConfiguration.setUri("/api/test/{id}");
        resourceConfiguration.setUrl("http://localhost/api/test/{id}");
        resourceConfiguration.setResourceType(MockResource.class);
        resourceConfiguration.setName(MockResource.class.getSimpleName());

        ResourceConfiguration targetedResourceConfiguration = new ResourceConfiguration();
        targetedResourceConfiguration.setUri("/api/targeted/{id}");
        targetedResourceConfiguration.setUrl("http://localhost/api/targeted/{id}");
        targetedResourceConfiguration.setResourceType(MockTargetedResource.class);
        targetedResourceConfiguration.setName(MockTargetedResource.class.getSimpleName());

        List<ResourceConfiguration> resources = new ArrayList<>();
        resources.add(resourceConfiguration);
        resources.add(targetedResourceConfiguration);
        apiConfiguration.setResources(resources);
    }

    @Test
    public void testHandleDefaultRequest() throws IOException, WebLayerException, ServiceLayerException {
        request.setRequestURI("/api/test/hasId");
        String content = "{ \"id\": \"hasId\"}";
        request.setContent(content.getBytes());
        ResponseEntity response = (ResponseEntity) handler.handleRequest(wrapper, apiConfiguration);
        assertEquals("hasId", ((MockResource) response.getBody()).getId());

        request.setRequestURI("/api/test/99");
        handler.handleRequest(wrapper, apiConfiguration);
        response = (ResponseEntity) handler.handleRequest(wrapper, apiConfiguration);
        assertEquals("99", ((MockResource) response.getBody()).getId());

        request.setRequestURI("/api/targeted/99");
        handler.handleRequest(wrapper, apiConfiguration);
        response = (ResponseEntity) handler.handleRequest(wrapper, apiConfiguration);
        assertEquals("99", ((MockTargetedResource) response.getBody()).getId());

    }

    @Test
    public void testEmptyBody() throws IOException, WebLayerException, ServiceLayerException {
        request.setRequestURI("/api/test/hasId");
        String content = "";
        request.setContent(content.getBytes());
        exception.expect(WebLayerException.class);
        exception.expectMessage("Could not put resource.");
        handler.handleRequest(wrapper, apiConfiguration);
    }

}
