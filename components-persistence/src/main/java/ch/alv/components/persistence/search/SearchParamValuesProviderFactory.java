package ch.alv.components.persistence.search;

import ch.alv.components.persistence.repository.ParamValuesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Registry to retrieve beans.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class SearchParamValuesProviderFactory {

    private static final Logger LOG = LoggerFactory.getLogger(SearchParamValuesProviderFactory.class);

    public static <T extends ParamValuesProvider> ParamValuesProvider createProvider(Class<T> providerClass) {
        try {
            return providerClass.newInstance();
        } catch (Exception e) {
            LOG.error("Error while creating SearchParamValuesProvider with name '" + providerClass.getName() + "'.", e);
            return null;
        }
    }

}
