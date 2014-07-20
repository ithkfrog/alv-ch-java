package ch.alv.components.web.api.handler;

import ch.alv.components.data.DataLayerException;
import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.config.QueryParameter;
import ch.alv.components.web.api.config.ResourceConfiguration;
import ch.alv.components.web.api.config.UriParameter;
import ch.alv.components.web.api.mock.MockResource;
import ch.alv.components.web.api.mock.MockTargetedResource;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
 * Unit tests for the {@link DefaultGetRequestHandler} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-request-handler-test-context.xml")
public class DefaultGetRequestHandlerTest {

    private MockHttpServletRequest request = new MockHttpServletRequest();

    private ApiConfiguration apiConfiguration = new ApiConfiguration();

    private HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request) ;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private GetRequestHandler handler;

    @Before
    public void init() {
        apiConfiguration = new ApiConfiguration();
        apiConfiguration.setBaseUri("http://localhost");

        ResourceConfiguration resourceConfiguration = new ResourceConfiguration();
        resourceConfiguration.setUri("/api/test");
        resourceConfiguration.setUrl("http://localhost/api/test");
        resourceConfiguration.setResourceType(MockResource.class);
        resourceConfiguration.setName(MockResource.class.getSimpleName());

        ResourceConfiguration resourceConfiguration2 = new ResourceConfiguration();
        QueryParameter parameter = new QueryParameter();
        parameter.setName("id");
        resourceConfiguration2.setUri("/api/test/{id}");
        resourceConfiguration2.setUrl("http://localhost/api/test/{id}");
        resourceConfiguration2.setResourceType(MockResource.class);
        resourceConfiguration2.getQueryParameters().add(parameter);
        resourceConfiguration2.setName(MockResource.class.getSimpleName());

        ResourceConfiguration targetedResourceConfiguration = new ResourceConfiguration();
        targetedResourceConfiguration.setUri("/api/targeted");
        targetedResourceConfiguration.setUrl("http://localhost/api/targeted");
        targetedResourceConfiguration.setResourceType(MockTargetedResource.class);
        targetedResourceConfiguration.setName(MockTargetedResource.class.getSimpleName());

        ResourceConfiguration targetedResourceConfiguration2 = new ResourceConfiguration();
        targetedResourceConfiguration2.setUri("/api/targeted/{id}");
        targetedResourceConfiguration2.setUrl("http://localhost/api/targeted/{id}");
        targetedResourceConfiguration2.setResourceType(MockTargetedResource.class);
        UriParameter parameter1 = new UriParameter();
        parameter1.setName("id");
        targetedResourceConfiguration2.getUriParameters().add(parameter1);
        targetedResourceConfiguration2.setName(MockTargetedResource.class.getSimpleName());

        List<ResourceConfiguration> resources = new ArrayList<>();
        resources.add(resourceConfiguration);
        resources.add(resourceConfiguration2);
        resources.add(targetedResourceConfiguration);
        resources.add(targetedResourceConfiguration2);
        apiConfiguration.setResources(resources);
    }

    @Test
    public void testDefaultRequests() throws IOException, WebLayerException, ServiceLayerException {
        request.removeAllParameters();
        ResponseEntity response;
        request.setRequestURI("/api/test/hasId");
        response = (ResponseEntity) handler.handleRequest(wrapper, apiConfiguration);
        assertEquals("99", ((MockResource) response.getBody()).getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        request.setRequestURI("/api/test/unknown");
        response = (ResponseEntity) handler.handleRequest(wrapper, apiConfiguration);
        assertEquals("MockResource not found.", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        request.setRequestURI("/api/test");
        response = (ResponseEntity) handler.handleRequest(wrapper, apiConfiguration);
        assertEquals(1, ((Page) response.getBody()).getContent().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        request.setRequestURI("/api/targeted");
        response = (ResponseEntity) handler.handleRequest(wrapper, apiConfiguration);
        assertEquals(1, ((Page) response.getBody()).getContent().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        request.setRequestURI("/api/targeted/hasId");
        response = (ResponseEntity) handler.handleRequest(wrapper, apiConfiguration);
        assertEquals("99", ((MockTargetedResource) response.getBody()).getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testPagedRequests() throws IOException, WebLayerException, ServiceLayerException {
        request.setRequestURI("/api/test");
        request.setParameter("queryName", "testQuery");
        exception.expect(DataLayerException.class);
        exception.expectMessage("Could not find query with name 'testQuery'.");
        handler.handleRequest(wrapper, apiConfiguration);
    }

    @Test
    public void testNamedQueryRequests() throws IOException, WebLayerException, ServiceLayerException {
        request.setRequestURI("/api/test");
        request.setParameter("pageNumber", "5");
        request.setParameter("pageSize", "200");
        ResponseEntity response = (ResponseEntity) handler.handleRequest(wrapper, apiConfiguration);
        assertEquals(5, ((Page) response.getBody()).getNumber());
        assertEquals(200, ((Page) response.getBody()).getSize());
        assertEquals(0, ((Page) response.getBody()).getNumberOfElements());
        assertEquals(1, ((Page) response.getBody()).getTotalElements());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
