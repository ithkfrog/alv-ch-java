package ch.alv.components.data.query;

import ch.alv.components.core.search.ValuesProvider;

import java.util.Map;

/**
 * Interface for a QueryFactory.
 *
 * @since 1.0.0
 */
public interface QueryFactory {

    <T> T createQuery(String queryName, ValuesProvider params, Map<String, Object> services, Class<?> targetClass) throws NoSuchQueryProviderException;

}
