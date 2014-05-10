package ch.alv.components.service.internal;

import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.PersistenceService;
import ch.alv.components.service.SearchService;

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

    public PersistenceService getPersistenceService(String serviceName) {
        if (StringHelper.isEmpty(serviceName)) {
            throw new IllegalStateException("Param 'serviceName' must not be empty.");
        }
        return contextProvider.getBeanByName(serviceName);
    }

    public SearchService getSearchService(String serviceName) {
        if (StringHelper.isEmpty(serviceName)) {
            throw new IllegalStateException("Param 'serviceName' must not be empty.");
        }
        return contextProvider.getBeanByName(serviceName);
    }

}
