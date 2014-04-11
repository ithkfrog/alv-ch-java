package ch.alv.components.service.common;

import ch.alv.components.core.spring.context.DefaultContextProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.persistence.PersistenceService;
import ch.alv.components.service.search.SearchService;

/**
 * Provides dynamic access to service beans.
 *
 * @since 1.0.0
 */
public class ServiceRegistry {

    public PersistenceService getPersistenceService(String serviceName) {
        if (StringHelper.isEmpty(serviceName)) {
            throw new IllegalStateException("Param 'serviceName' must not be empty.");
        }
        return DefaultContextProvider.getBeanByName(serviceName);
    }

    public SearchService getSearchService(String serviceName) {
        if (StringHelper.isEmpty(serviceName)) {
            throw new IllegalStateException("Param 'serviceName' must not be empty.");
        }
        return DefaultContextProvider.getBeanByName(serviceName);
    }

}
