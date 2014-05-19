package ch.alv.components.service;

import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.data.DataService;

/**
 * Provides dynamic access to service beans.
 *
 * @since 1.0.0
 */
public class ServiceRegistry {

    private final ApplicationContextProvider contextProvider;

    public ServiceRegistry(ApplicationContextProvider contextProvider) {
        this.contextProvider = contextProvider;
    }

    public DataService getDataService(String serviceName) {
        if (StringHelper.isEmpty(serviceName)) {
            throw new IllegalStateException("Param 'serviceName' must not be empty.");
        }
        return contextProvider.getBeanByName(serviceName);
    }

}
