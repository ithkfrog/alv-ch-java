package ch.alv.components.persistence.query.factory;

import ch.alv.components.persistence.query.SelectQuery;
import ch.alv.components.persistence.repository.ParamValuesProvider;
import ch.alv.components.persistence.search.Search;

/**
 * Interface for query creation strategies.
 *
 * @since 1.0.0
 */
public interface QueryFactory {

    String getName();

    SelectQuery createQuery(Search config, Class<?> entityClass, ParamValuesProvider valuesProvider);

}
