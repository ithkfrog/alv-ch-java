package ch.alv.components.web.endpoint;

import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.core.utils.StringHelper;

import java.util.Map;

/**
 * Spring beans implementation of the {@link EndpointProvider} interface.
 *
 * @since 1.0.0
 */
public class SpringBeansEndpointProvider implements EndpointProvider {

    private final ApplicationContextProvider contextProvider;

    public SpringBeansEndpointProvider(ApplicationContextProvider contextProvider) {
        this.contextProvider = contextProvider;
    }

    public Endpoint getEndpointStore(String moduleName, String storeName) {
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

    public Endpoint getEndpointByResource(Class<?> entityClass) {
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
