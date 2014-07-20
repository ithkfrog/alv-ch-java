package ch.alv.components.web.api.filter;

import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;

/**
 * Basic interface for request filters.
 *
 * @since 1.0.0
 */
public interface HttpRequestFilter {

    String getName();

    int getPriority();

    void filterRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws HttpRequestFilterException;

}
