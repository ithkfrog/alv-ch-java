package ch.alv.components.data.query;


import ch.alv.components.core.search.ValuesProvider;

import java.util.Map;

/**
 * Provides a concrete query.
 *
 * @since 1.0.0
 */
public interface QueryProvider {

    String getName();

    <T> T createQuery(ValuesProvider params, Map<String, Object> services, Class<?> entityClass);

}
