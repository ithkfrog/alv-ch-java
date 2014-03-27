package ch.alv.components.service.persistence;

import ch.admin.seco.tcsb.common.base.spring.DefaultContextProvider;
import ch.admin.seco.tcsb.common.base.utils.StringHelper;

/**
 * Provides easy access to PersistenceService beans.
 *
 * @author seco-hrf
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
