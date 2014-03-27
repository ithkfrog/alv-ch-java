package ch.alv.components.web.endpoint;

/**
 * HttpMethod filter for requests.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public interface EndpointHttpMethodFilter {

    EndpointHttpMethodFilterResult doFilter(String methodString, String moduleName, String storeName);

}
