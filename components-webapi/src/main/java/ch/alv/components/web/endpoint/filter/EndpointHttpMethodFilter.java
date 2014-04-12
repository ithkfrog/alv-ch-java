package ch.alv.components.web.endpoint.filter;

/**
 * HttpMethod filter for requests.
 *
 * @since 1.0.0
 */
public interface EndpointHttpMethodFilter {

    EndpointHttpMethodFilterResult doFilter(String methodString, String moduleName, String storeName) throws UnSupportedMethodException;

}
