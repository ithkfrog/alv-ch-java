package ch.alv.components.data.elastic;

import ch.alv.components.core.search.Search;
import ch.alv.components.core.search.ValuesProvider;


/**
 * Unit tests for the Repositories
 *
 * @since 1.0.0
 */
public class ElasticBaseSearchRepositoryTestSearch implements Search {

    @Override
    public Object createQuery(ValuesProvider valuesProvider, Class<?> targetClass) {
        return "";
    }

    @Override
    public String getName() {
        return "elasticBaseSearchRepositoryTestSearch";
    }
}
