package ch.alv.components.web.endpoint;

import ch.alv.components.core.spring.context.DefaultContextProvider;
import ch.alv.components.core.utils.StringHelper;

import java.util.Map;

/**
 * Provides easy access to WebApiEndpoints.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class EndpointRegistry {

    public static Endpoint getEndpoint(String moduleName, String storeName) {
        if (StringHelper.isEmpty(moduleName)) {
            throw new IllegalStateException("Param 'moduleName' must not be empty.");
        }
        if (StringHelper.isEmpty(storeName)) {
            throw new IllegalStateException("Param 'storeName' must not be empty.");
        }
        Map<String, Endpoint> endpointMap = DefaultContextProvider.getBeansOfType(Endpoint.class);
        if (endpointMap == null || endpointMap.isEmpty()) {
            return null;
        }
        for (String key : endpointMap.keySet()) {
            Endpoint endpoint = endpointMap.get(key);
            if (endpoint.getModuleName().equalsIgnoreCase(moduleName)
                    && endpoint.getStoreName().equalsIgnoreCase(storeName)) {
                return endpoint;
            }
        }
        return null;
    }

    public static Endpoint getEndpoint(Class<?> entityClass) {
        if (entityClass == null) {
            throw new IllegalStateException("Param 'entityClass' must not be null.");
        }
        Map<String, Endpoint> endpointMap = DefaultContextProvider.getBeansOfType(Endpoint.class);
        if (endpointMap == null || endpointMap.isEmpty()) {
            return null;
        }
        for (String key : endpointMap.keySet()) {
            Endpoint endpoint = endpointMap.get(key);
            if (endpoint.getEntityClass() == entityClass) {
                return endpoint;
            }
        }
        return null;
    }

}
