package ch.alv.components.web.api.mock;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.handler.GetRequestHandler;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;

/**
 * Mock implementation of the {@link GetRequestHandler} interface.
 *
 * @since 1.0.0
 */
public class MockGetRequestHandler implements GetRequestHandler {
    @Override
    public Object handleRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws ServiceLayerException {
        return "GET";
    }
}
