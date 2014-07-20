package ch.alv.components.web.api.controller;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.filter.HttpRequestFilter;
import ch.alv.components.web.api.filter.HttpRequestFilterException;
import ch.alv.components.web.api.handler.DeleteRequestHandler;
import ch.alv.components.web.api.handler.GetRequestHandler;
import ch.alv.components.web.api.handler.PostRequestHandler;
import ch.alv.components.web.api.handler.PutRequestHandler;
import ch.alv.components.web.api.mock.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.web.api.filter.HttpRequestFilterComparator} class.
 *
 * @since 1.0.0
 */
public class DefaultFrontControllerTest {

    private DefaultFrontController controller = new DefaultFrontController();

    private final GetRequestHandler getRequestHandler = new MockGetRequestHandler();
    private final PostRequestHandler postRequestHandler = new MockPostRequestHandler();
    private final PutRequestHandler putRequestHandler = new MockPutRequestHandler();
    private final DeleteRequestHandler deleteRequestHandler = new MockDeleteRequestHandler();

    private final HttpRequestFilter filter = new MockHttpRequestFilter("defaultFilter", 1);
    private final List<HttpRequestFilter> filterList = new ArrayList<>();
    private final HttpRequestFilter throwingFilter = new MockThrowingHttpRequestFilter("throwingFilter", 2);

    private final ApiConfiguration apiConfiguration = new ApiConfiguration();

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Before
    public void init() {
        controller.setGetRequestHandler(getRequestHandler);
        controller.setPostRequestHandler(postRequestHandler);
        controller.setPutRequestHandler(putRequestHandler);
        controller.setDeleteRequestHandler(deleteRequestHandler);
        filterList.add(filter);
        controller.setFilters(filterList);
        controller.setApiConfiguration(apiConfiguration);
    }

    private void resetFilters() {
        List<HttpRequestFilter> filterList = new ArrayList<>();
        filterList.add(filter);
        controller.setFilters(filterList);
    }

    @Test
    public void testGetters() {
        resetFilters();
        assertEquals(apiConfiguration, controller.getApiConfiguration());
        assertEquals(getRequestHandler, controller.getGetRequestHandler());
        assertEquals(postRequestHandler, controller.getPostRequestHandler());
        assertEquals(putRequestHandler, controller.getPutRequestHandler());
        assertEquals(deleteRequestHandler, controller.getDeleteRequestHandler());
        assertEquals(filterList, controller.getFilters());
    }

    @Test
    public void testHandleGET() throws HttpRequestFilterException, ServiceLayerException {
        resetFilters();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        assertEquals("GET", controller.handleGetRequest(request));
    }

    @Test
    public void testHandlePOST() throws HttpRequestFilterException, ServiceLayerException, IOException, WebLayerException {
        resetFilters();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        assertEquals("POST", controller.handlePostRequest(request));
    }

    @Test
    public void testHandlePUT() throws HttpRequestFilterException, ServiceLayerException, IOException, WebLayerException {
        resetFilters();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("PUT");
        assertEquals("PUT", controller.handlePutRequest(request));
    }

    @Test
    public void testHandleDELETE() throws HttpRequestFilterException, ServiceLayerException, WebLayerException {
        resetFilters();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("DELETE");
        assertEquals("DELETE", controller.handleDeleteRequest(request));
    }

    @Test
    public void testException() throws HttpRequestFilterException, ServiceLayerException, WebLayerException {
        resetFilters();
        controller.addFilter(throwingFilter);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("DELETE");
        exception.expect(HttpRequestFilterException.class);
        exception.expectMessage("throwingFilter");
        controller.handleDeleteRequest(request);
    }

    @Test
    public void testIllegalFiltersSetterArguments() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Filters to add must not be null or empty.");
        controller.setFilters(null);
    }

    @Test
    public void testIllegalFilterSetterArguments() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Filter to add must not be null.");
        controller.addFilter(null);
    }
}
