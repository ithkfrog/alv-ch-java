package ch.alv.components.web.api.mock;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.handler.PostRequestHandler;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;

/**
 * Mock implementation of the {@link ch.alv.components.web.api.handler.PostRequestHandler} interface.
 *
 * @since 1.0.0
 */
public class MockPostRequestHandler implements PostRequestHandler {

    @Override
    public Object handleRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws ServiceLayerException {
        return "POST";
    }
}
