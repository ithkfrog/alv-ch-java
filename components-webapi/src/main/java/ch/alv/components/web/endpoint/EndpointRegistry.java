package ch.alv.components.web.endpoint;

import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.core.utils.StringHelper;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Provides easy access to WebApiEndpoints.
 *
 * @since 1.0.0
 */
public class EndpointRegistry {

    @Resource
    private ApplicationContextProvider contextProvider;

    public Endpoint getEndpoint(String moduleName, String storeName) {
        if (StringHelper.isEmpty(moduleName)) {
            throw new IllegalArgumentException("Param 'moduleName' must not be empty.");
        }
        if (StringHelper.isEmpty(storeName)) {
            throw new IllegalArgumentException("Param 'storeName' must not be empty.");
        }
        Map<String, Endpoint> endpointMap = contextProvider.getBeansOfType(Endpoint.class);
        for (String key : endpointMap.keySet()) {
            Endpoint endpoint = endpointMap.get(key);
            if (endpoint.getModuleName().equalsIgnoreCase(moduleName)
                    && endpoint.getStoreName().equalsIgnoreCase(storeName)) {
                return endpoint;
            }
        }
        return null;
    }

    public Endpoint getEndpoint(Class<?> entityClass) {
        if (entityClass == null) {
            throw new IllegalArgumentException("Param 'entityClass' must not be null.");
        }
        Map<String, Endpoint> endpointMap = contextProvider.getBeansOfType(Endpoint.class);
        for (String key : endpointMap.keySet()) {
            Endpoint endpoint = endpointMap.get(key);
            if (endpoint.getEntityClass() == entityClass) {
                return endpoint;
            }
        }
        return null;
    }


}
