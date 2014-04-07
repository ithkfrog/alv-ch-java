package ch.alv.components.persistence.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to silently instantiate subclasses of the {@link ValuesProvider} interface.
 *
 * @since 1.0.0
 */
public class ValuesProviderFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ValuesProviderFactory.class);

    public static <T extends ValuesProvider> ValuesProvider createProvider(Class<T> providerClass) {
        try {
            return providerClass.newInstance();
        } catch (Exception e) {
            LOG.error("Error while creating ValuesProvider with name '" + providerClass.getName() + "'.", e);
            return null;
        }
    }

}
