package ch.alv.components.web.api.handler;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.config.ResourceConfiguration;
import ch.alv.components.web.api.mock.MockTargetedResource;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the {@link DefaultDeleteRequestHandler} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-request-handler-test-context.xml")
public class DefaultDeleteRequestHandlerTest {

    private MockHttpServletRequest request = new MockHttpServletRequest();

    private ApiConfiguration apiConfiguration = new ApiConfiguration();

    private HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request) ;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private DeleteRequestHandler handler;

    @Before
    public void init() {
        apiConfiguration = new ApiConfiguration();
        apiConfiguration.setBaseUri("http://localhost");

        ResourceConfiguration resourceConfiguration = new ResourceConfiguration();
        resourceConfiguration.setUri("/api/test/{id}");
        resourceConfiguration.setUrl("http://localhost/api/test/{id}");
        resourceConfiguration.setResourceType(MockTargetedResource.class);
        resourceConfiguration.setName(MockTargetedResource.class.getSimpleName());

        List<ResourceConfiguration> resources = new ArrayList<>();
        resources.add(resourceConfiguration);
        apiConfiguration.setResources(resources);

    }

    @Test
    public void testHandleDefaultRequest() throws ServiceLayerException, WebLayerException {

        request.setRequestURI("/api/test/hasId");
        handler.handleRequest(wrapper, apiConfiguration);

        exception.expect(WebLayerException.class);
        request.setRequestURI("/api/test/99");
        handler.handleRequest(wrapper, apiConfiguration);
    }

}
