package ch.alv.components.persistence.query.factory;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.persistence.query.SelectQuery;
import ch.alv.components.persistence.query.internal.QueryOperator;
import ch.alv.components.persistence.repository.ParamValuesProvider;
import ch.alv.components.persistence.search.Search;

import java.util.HashMap;
import java.util.Map;

/**
 * Combines all parameters to an AND-related query.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class AndQueryFactoryImpl implements QueryFactory {

    @Override
    public String getName() {
        return "andQueryFactoryImpl";
    }

    @Override
    public SelectQuery createQuery(Search config, Class<?> entityClass, ParamValuesProvider valuesProvider) {
        SelectQuery query = new SelectQuery();
        String token = entityClass.getSimpleName().substring(0, 1).toLowerCase();
        query.select(new String[]{token + ".*"}).from(token, entityClass);

        // if no valuesProvider exists, the work is done here...
        if (valuesProvider == null) {
            return query;
        }

        Map<String, Object> params = valuesProvider.getParams();
        // ... same case, if the params map is empty.
        if (params.isEmpty()) {
            return query;
        }

        // Map to hold the converted non-empty values
        Map<String, Object> updatedValues = new HashMap<>();

        // Know if there was already a valid param added. If true, .where() must be called, else .and().
        boolean isFirst = true;

        // Walk through the params and add them if not empty.
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                isFirst = addParam(query, token, isFirst, key);
                updatedValues.put(token + StringHelper.capitalize(key), params.get(key));
            }
        }
        // Set the converted values on the query.
        query.setValues(updatedValues);
        return query;
    }

    private boolean addParam(SelectQuery query, String token, boolean first, String key) {
        // call .where() if it's the first (valid) param.
        if (first) {
            query.where(token, key, QueryOperator.EQUALS);
            first = false;
        } else {
            query.and(token, key);
        }
        return first;
    }
}
