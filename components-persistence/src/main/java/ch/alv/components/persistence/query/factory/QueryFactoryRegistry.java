package ch.alv.components.persistence.query.factory;

import ch.alv.components.core.spring.context.DefaultContextProvider;

import java.util.Map;

/**
 * Registry to retrieve beans.
 *
 * @since 1.0.0
 */
public class QueryFactoryRegistry {

    public static QueryFactory getFactory(String strategyName) {
        Map<String, QueryFactory> strategies = DefaultContextProvider.getBeansOfType(QueryFactory.class);

        if (strategies.isEmpty()) {
            return null;
        }
        for (String key : strategies.keySet()) {
            if (strategies.get(key).getName().equals(strategyName)) {
                return strategies.get(key);
            }
        }
        return null;
    }

}
