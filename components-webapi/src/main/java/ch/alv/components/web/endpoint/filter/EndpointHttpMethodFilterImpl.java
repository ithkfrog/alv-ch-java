package ch.alv.components.web.endpoint.filter;

import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import org.springframework.http.HttpMethod;

/**
 * Default implementation of the {@link SecurityFilter} interface.
 *
 * @since 1.0.0
 */
public class EndpointHttpMethodFilterImpl implements EndpointHttpMethodFilter {

    @Override
    public EndpointHttpMethodFilterResult doFilter(String methodString, String moduleName, String storeName) {
        Endpoint endpoint = EndpointRegistry.getEndpoint(moduleName, storeName);

        if (endpoint == null) {
            throw new IllegalStateException("No endpoint for store '" + moduleName + "/" + storeName + "' found.");
        }

        if (endpoint.getAllowedMethods().indexOf(getMethodFromString(methodString)) > -1) {
            return new EndpointHttpMethodFilterResult(EndpointHttpMethodFilterResult.OK, "Method allowed.");
        }
        return new EndpointHttpMethodFilterResult(EndpointHttpMethodFilterResult.NOK, "Method " + methodString + " not allowed for store '" + moduleName + "/" + storeName + "'.");
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
        return HttpMethod.DELETE;
    }
}
