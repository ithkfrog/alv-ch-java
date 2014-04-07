package ch.alv.components.persistence.search;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.persistence.search.internal.*;

import java.util.List;
import java.util.Map;

/**
 * Jpa adapter for searches which knows how to render the search.
 *
 * @since 1.0.0
 */
public class JpaDynamicSearchAdapter implements Renderable {

    enum JpaConcatType {

        AND(ConcatType.AND, " AND "),
        OR(ConcatType.OR, " OR ");

        private ConcatType concatType;

        private String jpaRepresentation;

        private JpaConcatType(ConcatType concatType, String jpaRepresentation) {
            this.concatType = concatType;
            this.jpaRepresentation = jpaRepresentation;
        }

        public static JpaConcatType getForConcatType(ConcatType concatType) {
            for (JpaConcatType type : values()) {
                if (type.getConcatType() == concatType) {
                    return type;
                }
            }
            return null;
        }

        public ConcatType getConcatType() {
            return concatType;
        }

        public String render() {
            return jpaRepresentation;
        }
    }

    enum JpaComparatorType {

        EQUALS(ComparatorType.EQUALS, " = "),
        NOT_EQUALS(ComparatorType.NOT_EQUALS, " != "),
        LIKE(ComparatorType.LIKE, " LIKE "),
        NOT_LIKE(ComparatorType.NOT_LIKE, " NOT LIKE "),
        GT(ComparatorType.GT, " > "),
        GE(ComparatorType.GE, " >= "),
        LT(ComparatorType.LT, " < "),
        LE(ComparatorType.LE, " <= "),
        IN(ComparatorType.IN, " IN "),
        NOT_IN(ComparatorType.NOT_IN, " NOT IN ");


        private ComparatorType comparatorType;

        private String jpaRepresentation;

        private JpaComparatorType(ComparatorType comparatorType, String jpaRepresentation) {
            this.comparatorType = comparatorType;
            this.jpaRepresentation = jpaRepresentation;
        }

        public static JpaComparatorType getForComparatorType(ComparatorType comparatorType) {
            for (JpaComparatorType type : values()) {
                if (type.getComparatorType() == comparatorType) {
                    return type;
                }
            }
            return null;
        }

        public ComparatorType getComparatorType() {
            return comparatorType;
        }

        public String render() {
            return jpaRepresentation;
        }
    }

    enum JpaSortType {

        AND(SortType.ASC, " ASC"),
        OR(SortType.DESC, " DESC");

        private SortType sortType;

        private String jpaRepresentation;

        private JpaSortType(SortType sortType, String jpaRepresentation) {
            this.sortType = sortType;
            this.jpaRepresentation = jpaRepresentation;
        }

        public static JpaSortType getForSortType(SortType sortType) {
            for (JpaSortType type : values()) {
                if (type.getSortType() == sortType) {
                    return type;
                }
            }
            return null;
        }

        public SortType getSortType() {
            return sortType;
        }

        public String render() {
            return jpaRepresentation;
        }
    }

    private final DynamicSearch search;

    public JpaDynamicSearchAdapter(DynamicSearch search) {
        this.search = search;
    }

    public void setEntityClass(Class<?> entityClass) {
        search.from(entityClass.getSimpleName().substring(0,1).toLowerCase(), entityClass);
    }

    @Override
    public String render() {
        return render(null);
    }

    @Override
    public String render(ValuesProvider valuesProvider) {

        StringBuilder sb = renderRootToStringBuilder();
        boolean hasPredicates = false;

        StringBuilder localStringBuilder = new StringBuilder();
        if (valuesProvider != null) {
            // Render items and add the query as text to the global StringBuilder
            if (renderPredicates(valuesProvider.getValues(), hasPredicates, localStringBuilder)) {
                sb.append(" WHERE ");
                sb.append(localStringBuilder.toString());
            }
        } else {
            // Render items and add the query as text to the global StringBuilder
            if (renderPredicates(null, hasPredicates, localStringBuilder)) {
                sb.append(" WHERE ");
                sb.append(localStringBuilder.toString());
            }
        }
        renderSortings(sb);
        return sb.toString();
    }

    private void renderSortings(StringBuilder sb) {
        boolean isFirst = true;
        if (!search.getSortings().isEmpty()) {
            for (Sorting sorting : search.getSortings()) {
                if (isFirst) {
                    sb.append(" ORDER BY " + sorting.getEntityToken() + "." + sorting.getName() + JpaSortType.getForSortType(sorting.getType()).render());
                    isFirst = false;
                } else {
                    sb.append(", " + sorting.getEntityToken() + "." + sorting.getName() + JpaSortType.getForSortType(sorting.getType()).render());
                }
            }
        }
    }

    private StringBuilder renderRootToStringBuilder() {
        StringBuilder sb = new StringBuilder();
        renderProjections(sb);
        renderTables(sb);
        return sb;
    }

    private void renderProjections(StringBuilder sb) {
        sb.append("SELECT ");
        boolean isFirst = true;
        for (String projection : search.getSelects()) {
            String[] tokens = projection.split("\\.");
            String attribute = tokens[0];

            if (tokens.length > 1 && StringHelper.isNotEmpty(tokens[1]) && !tokens[1].equals("*")) {
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

    private void renderTables(StringBuilder sb) {
        sb.append(" FROM ");
        boolean isFirst = true;
        for (Entity entity : search.getFroms()) {
            if (isFirst) {
                isFirst = false;
                sb.append(entity.getEntityClass().getSimpleName() + " " + entity.getEntityToken());
            } else {
                sb.append(", " + entity.getEntityClass().getSimpleName() + " " + entity.getEntityToken());
            }
        }
    }

    private boolean renderPredicates(Map<String, Object> values, boolean hasPredicates, StringBuilder localStringBuilder) {
        if (search.getWheres().size() > 0 && hasMatchingParamValues(values)) {
            for (Predicate predicateItem : search.getWheres()) {
                hasPredicates = renderPredicateItem(predicateItem, localStringBuilder, values, hasPredicates);
            }
        }
        return hasPredicates;
    }


    private boolean hasMatchingParamValues(Map<String, Object> paramValues) {
        for (Predicate predicateItem : search.getWheres()) {
            if (predicateItem instanceof PredicateItem) {
                if (((PredicateItem) predicateItem).getImmutableValue() != null) {
                    return true;
                }
                return hasMatchingParamValue((PredicateItem) predicateItem, paramValues);
            } else if (predicateItem instanceof PredicateGroup) {
                PredicateGroup predicateGroup = (PredicateGroup) predicateItem;
                return hasMatchingParamValues(search.getWheres(), paramValues);
            }
        }
        return false;
    }

    private boolean hasMatchingParamValues(List<Predicate> items, Map<String, Object> paramValues) {
        for (Predicate predicateItem : items) {
            if (predicateItem instanceof PredicateItem) {
                return hasMatchingParamValue((PredicateItem) predicateItem, paramValues);
            } else if (predicateItem instanceof PredicateGroup) {
                PredicateGroup predicateGroup = (PredicateGroup) predicateItem;
                return hasMatchingParamValues(predicateGroup.getPredicates(), paramValues);
            }
        }
        return false;
    }

    private boolean hasMatchingParamValue(PredicateItem predicate, Map<String, Object> paramValues) {
        if (predicate.getImmutableValue() != null) {
            return true;
        }
        if (paramValues == null || paramValues.isEmpty()) {
            return false;
        }
        if (paramValues.containsKey(predicate.getEntityToken() + StringHelper.capitalize(predicate.getName()))) {
            return true;
        }
        return false;
    }

    private boolean renderPredicateItem(Predicate predicateItem, StringBuilder sb, Map<String, Object> paramValues, boolean hasPreDecessor) {
        if (predicateItem instanceof PredicateGroup) {
            return render((PredicateGroup) predicateItem, sb, paramValues, hasPreDecessor);
        } else {
            return render((PredicateItem) predicateItem, sb, paramValues, hasPreDecessor);
        }
    }

    private boolean render(PredicateGroup group, StringBuilder sb, Map<String, Object> paramValues, boolean hasPreDecessor) {
        StringBuilder localSb = new StringBuilder();
        boolean hasPreDecessorKid = false;
        for (Predicate item : group.getPredicates()) {
            hasPreDecessorKid = renderPredicateItem(item, localSb, paramValues, hasPreDecessorKid);
        }

        if (hasPreDecessorKid) {
            if (hasPreDecessor) {
                sb.append(JpaConcatType.getForConcatType(group.getConcatType()).render());
            }
            sb.append("(");
            sb.append(localSb.toString());
            sb.append(")");
            return true;
        }
        return false;
    }

    private boolean render(PredicateItem item, StringBuilder sb, Map<String, Object> paramValues, boolean hasPreDecessor) {
        Object value = null;
        if (paramValues != null) {
            value = paramValues.get(item.getEntityToken() + StringHelper.capitalize(item.getName()));
        }
        if (item.getImmutableValue() != null) {
            sb.append(item.getEntityToken() + "." + item.getName() + JpaComparatorType.getForComparatorType(item.getComparator()).render() + item.getImmutableValue());
            return true;
        } else if (value != null) {
            if (hasPreDecessor) {
                sb.append(JpaConcatType.getForConcatType(item.getConcatType()).render());
            }
            if (StringHelper.isEmpty(item.getImmutableValue())) {
                if (value instanceof String) {
                    if (StringHelper.isNotEmpty((String) value)) {
                        sb.append(item.getEntityToken() + "." + item.getName() + JpaComparatorType.getForComparatorType(item.getComparator()).render() + ":" + item.getEntityToken() + StringHelper.capitalize(item.getName()));
                        return true;
                    }
                } else {
                    sb.append(item.getEntityToken() + "." + item.getName() + JpaComparatorType.getForComparatorType(item.getComparator()).render() + ":" + item.getEntityToken() + StringHelper.capitalize(item.getName()));
                    return true;
                }
            }
        }
        return false;
    }
}
