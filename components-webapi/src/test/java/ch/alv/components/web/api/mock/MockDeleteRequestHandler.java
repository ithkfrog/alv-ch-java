package ch.alv.components.web.api.mock;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.handler.DeleteRequestHandler;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;

/**
 * Mock implementation of the {@link ch.alv.components.web.api.handler.DeleteRequestHandler} interface.
 *
 * @since 1.0.0
 */
public class MockDeleteRequestHandler implements DeleteRequestHandler {
    @Override
    public Object handleRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws ServiceLayerException {
        return "DELETE";
    }
}
