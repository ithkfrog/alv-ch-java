package ch.alv.components.web.api.handler;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;

/**
 * Request handler for removal requests.
 *
 * @since 1.0.0
 */
public interface DeleteRequestHandler {

    Object handleRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws ServiceLayerException, WebLayerException;

}
