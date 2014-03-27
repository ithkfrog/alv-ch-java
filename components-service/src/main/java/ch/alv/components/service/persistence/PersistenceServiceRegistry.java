package ch.alv.components.service.persistence;

import ch.alv.components.core.spring.context.DefaultContextProvider;
import ch.alv.components.core.utils.StringHelper;

/**
 * Provides easy access to PersistenceService beans.
 *
 * @since 1.0.0
 */
public class PersistenceServiceRegistry {

    public static PersistenceService getService(String serviceName) {
        if (StringHelper.isEmpty(serviceName)) {
            throw new IllegalStateException("Param 'serviceName' must not be empty.");
        }
        return DefaultContextProvider.getBeanByName(serviceName);
    }

}
