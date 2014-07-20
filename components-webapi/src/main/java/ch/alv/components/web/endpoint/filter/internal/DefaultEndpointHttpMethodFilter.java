package ch.alv.components.web.endpoint.filter.internal;

import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.SpringBeansEndpointProvider;
import ch.alv.components.web.endpoint.filter.EndpointHttpMethodFilter;
import ch.alv.components.web.endpoint.filter.UnSupportedMethodException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;

/**
 * Default implementation of the {@link ch.alv.components.web.endpoint.filter.SecurityFilter} interface.
 *
 * @since 1.0.0
 */
public class DefaultEndpointHttpMethodFilter implements EndpointHttpMethodFilter {

    @Resource
    private SpringBeansEndpointProvider springBeansEndpointProvider;

    @Override
    public EndpointHttpMethodFilterResult doFilter(String methodString, String moduleName, String storeName) throws UnSupportedMethodException, WebLayerException {
        Endpoint endpoint = springBeansEndpointProvider.getEndpointStore(moduleName, storeName);

        if (endpoint == null) {
            throw new WebLayerException("No endpoint for store '" + moduleName + "/" + storeName + "' found.", HttpStatus.NOT_FOUND);
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
