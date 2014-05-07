package ch.alv.components.web.endpoint.filter.internal;

import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import ch.alv.components.web.endpoint.filter.EndpointHttpMethodFilter;
import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import org.springframework.http.HttpMethod;

import javax.annotation.Resource;

/**
 * Default implementation of the {@link ch.alv.components.web.endpoint.filter.SecurityFilter} interface.
 *
 * @since 1.0.0
 */
public class DefaultEndpointHttpMethodFilter implements EndpointHttpMethodFilter {

    @Resource
    private EndpointRegistry endpointRegistry;

    @Override
    public EndpointHttpMethodFilterResult doFilter(String methodString, String moduleName, String storeName) {
        Endpoint endpoint = endpointRegistry.getEndpoint(moduleName, storeName);

        if (endpoint == null) {
            throw new IllegalStateException("No endpoint for store '" + moduleName + "/" + storeName + "' found.");
        }

        if (endpoint.getAllowedMethods().indexOf(getMethodFromString(methodString)) > -1) {
            return new EndpointHttpMethodFilterResult(EndpointHttpMethodFilterResult.OK, "Method allowed.");
        }
        throw new UnSupportedMethodException();
    }

    private HttpMethod getMethodFromString(String methodString) {
        if ("GET".equals(methodString)) {
            return HttpMethod.GET;
        }
        if ("POST".equals(methodString)) {
            return HttpMethod.POST;
        }
        if ("PUT".equals(methodString)) {
            return HttpMethod.PUT;
        }
        if ("DELETE".equals(methodString)) {
            return HttpMethod.DELETE;
        }
        return null;
    }
}
