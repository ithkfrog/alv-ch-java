package ch.alv.components.persistence.query.renderer;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.persistence.query.SelectQuery;
import ch.alv.components.persistence.query.internal.QueryEntity;
import ch.alv.components.persistence.query.internal.QueryPredicate;
import ch.alv.components.persistence.query.internal.QueryPredicateGroup;
import ch.alv.components.persistence.query.internal.QueryPredicateItem;

import java.util.List;
import java.util.Map;


/**
 * Dynamic query renderer
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class JpaQueryRenderer {

    public static String render(SelectQuery query) {
        // Prepare components
        Map<String, Object> values = query.getValues();
        StringBuilder sb = prepareGlobalStringBuilder(query);
        boolean hasPredicates = false;
        StringBuilder localStringBuilder = new StringBuilder();

        // Render items and add the query as text to the global StringBuilder
        if (renderPredicateItems(query, values, hasPredicates, localStringBuilder)) {
            sb.append(" WHERE ");
            sb.append(localStringBuilder.toString());
        }
        return sb.toString();
    }

    private static StringBuilder prepareGlobalStringBuilder(SelectQuery query) {
        StringBuilder sb;

        if (StringHelper.isEmpty(query.getBaseQuery())) {
            sb = renderRoot(query);
            query.setBaseQuery(sb.toString());
        } else {
            sb = new StringBuilder();
            sb.append(query.getBaseQuery());
        }
        return sb;
    }

    private static boolean renderPredicateItems(SelectQuery query, Map<String, Object> values, boolean hasPredicates, StringBuilder localStringBuilder) {
        if (query.getWheres().size() > 0 && hasMatchingParamValues(query, values)) {
            for (QueryPredicateItem predicateItem : query.getWheres()) {
                hasPredicates = renderPredicateItem(predicateItem, localStringBuilder, values, hasPredicates);
            }
        }
        return hasPredicates;
    }


    private static boolean hasMatchingParamValues(SelectQuery query, Map<String, Object> paramValues) {
        for (QueryPredicateItem predicateItem : query.getWheres()) {
            if (predicateItem instanceof QueryPredicate) {
                if (((QueryPredicate) predicateItem).getImmutableValue() != null) {
                    return true;
                }
                return hasMatchingParamValue((QueryPredicate) predicateItem, paramValues);
            } else if (predicateItem instanceof QueryPredicateGroup) {
                QueryPredicateGroup predicateGroup = (QueryPredicateGroup) predicateItem;
                return hasMatchingParamValues(query.getWheres(), paramValues);
            }
        }
        return false;
    }

    private static boolean hasMatchingParamValues(List<QueryPredicateItem> items, Map<String, Object> paramValues) {
        if (paramValues == null || paramValues.size() == 0) {
            return false;
        }
        for (QueryPredicateItem predicateItem : items) {
            if (predicateItem instanceof QueryPredicate) {
                return hasMatchingParamValue((QueryPredicate) predicateItem, paramValues);
            } else if (predicateItem instanceof QueryPredicateGroup) {
                QueryPredicateGroup predicateGroup = (QueryPredicateGroup) predicateItem;
                return hasMatchingParamValues(predicateGroup.getPredicates(), paramValues);
            }
        }
        return false;
    }

    private static boolean hasMatchingParamValue(QueryPredicate predicate, Map<String, Object> paramValues) {
        if (paramValues.containsKey(predicate.getEntityToken() + StringHelper.capitalize(predicate.getName())) ||
                predicate.getImmutableValue() != null) {
            return true;
        }
        return false;
    }

    private static boolean renderPredicateItem(QueryPredicateItem predicateItem, StringBuilder sb, Map<String, Object> paramValues, boolean hasPreDecessor) {
        if (predicateItem instanceof QueryPredicateGroup) {
            return render((QueryPredicateGroup) predicateItem, sb, paramValues, hasPreDecessor);
        } else {
            return render((QueryPredicate) predicateItem, sb, paramValues, hasPreDecessor);
        }
    }

    private static boolean render(QueryPredicateGroup group, StringBuilder sb, Map<String, Object> paramValues, boolean hasPreDecessor) {
        StringBuilder localSb = new StringBuilder();
        boolean hasPreDecessorKid = false;
        for (QueryPredicateItem item : group.getPredicates()) {
            hasPreDecessorKid = renderPredicateItem(item, localSb, paramValues, hasPreDecessorKid);
        }

        if (hasPreDecessorKid) {
            if (hasPreDecessor) {
                sb.append(group.getCombination().render());
            }
            sb.append("(");
            sb.append(localSb.toString());
            sb.append(")");
            return true;
        }
        return false;
    }

    private static boolean render(QueryPredicate item, StringBuilder sb, Map<String, Object> paramValues, boolean hasPreDecessor) {
        Object value = paramValues.get(item.getEntityToken() + StringHelper.capitalize(item.getName()));
        if (item.getImmutableValue() != null) {
            sb.append(item.getEntityToken() + "." + item.getName() + item.getOperator().render() + item.getImmutableValue());
            return true;
        } else if (value != null) {
            if (hasPreDecessor) {
                sb.append(item.getCombination().render());
            }
            if (StringHelper.isEmpty(item.getImmutableValue())) {
                if (value instanceof String) {
                    if (StringHelper.isNotEmpty((String) value)) {
                        sb.append(item.getEntityToken() + "." + item.getName() + item.getOperator().render() + ":" + item.getEntityToken() + StringHelper.capitalize(item.getName()));
                        return true;
                    }
                } else {
                    sb.append(item.getEntityToken() + "." + item.getName() + item.getOperator().render() + ":" + item.getEntityToken() + StringHelper.capitalize(item.getName()));
                    return true;
                }
            }
        }
        return false;
    }

    private static StringBuilder renderRoot(SelectQuery query) {
        StringBuilder sb = new StringBuilder();
        renderProjections(query, sb);
        renderTables(query, sb);
        return sb;
    }

    private static void renderProjections(SelectQuery query, StringBuilder sb) {
        sb.append("SELECT ");
        boolean isFirst = true;
        for (String projection : query.getSelects()) {
            String[] tokens = projection.split("\\.");
            String attribute = tokens[0];

            if (StringHelper.isNotEmpty(tokens[1]) && !tokens[1].equals("*")) {
                attribute = projection;
            }

            if (isFirst) {
                isFirst = false;
                sb.append(attribute);
            } else {
                sb.append(", " + attribute);
            }
        }
    }

    private static void renderTables(SelectQuery query, StringBuilder sb) {
        sb.append(" FROM ");
        boolean isFirst = true;
        for (QueryEntity entity : query.getFroms()) {
            if (isFirst) {
                isFirst = false;
                sb.append(entity.getEntityClass().getSimpleName() + " " + entity.getEntityToken());
            } else {
                sb.append(", " + entity.getEntityClass().getSimpleName() + " " + entity.getEntityToken());
            }
        }
    }

}
