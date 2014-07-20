package ch.alv.components.web.api.handler;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;

/**
 * Request handlers for get requests.
 *
 * @since 1.0.0
 */
public interface GetRequestHandler {

    Object handleRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws ServiceLayerException;

}
