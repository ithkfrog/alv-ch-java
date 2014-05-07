package ch.alv.components.web.endpoint.filter;

import ch.alv.components.web.endpoint.filter.internal.EndpointHttpMethodFilterResult;

/**
 * HttpMethod filter for requests.
 *
 * @since 1.0.0
 */
public interface EndpointHttpMethodFilter {

    EndpointHttpMethodFilterResult doFilter(String methodString, String moduleName, String storeName) throws UnSupportedMethodException;

}
