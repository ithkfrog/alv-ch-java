package ch.alv.components.web.api.handler;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;

import java.io.IOException;

/**
 * Request handler for put requests.
 *
 * @since 1.0.0
 */
public interface PutRequestHandler {

    Object handleRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws WebLayerException, IOException, ServiceLayerException;

}
