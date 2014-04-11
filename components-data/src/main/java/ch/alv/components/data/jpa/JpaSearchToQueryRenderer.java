package ch.alv.components.data.jpa;

import ch.alv.components.core.search.*;
import ch.alv.components.core.utils.StringHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * JPA implementation of the {@link SearchRenderer} interface.
 *
 * @since 1.0.0
 */
public class JpaSearchToQueryRenderer extends BaseSearchRendererImpl {

    /**
     * Constructor
     *
     * @param defaultSearch search configuration to use if no custom configuration is passed.
     */
    public JpaSearchToQueryRenderer(SearchImpl defaultSearch) {
        super(defaultSearch);
    }

    /* (non-Javadoc)
     * @see ch.alv.components.core.search.BaseSearchRendererImpl#doRender(ch.alv.components.core.search.SearchImpl, ch.alv.components.core.search.ValuesProvider)
     */
    @Override
    protected String doRender(Search search, ValuesProvider valuesProvider) {
        StringBuilder sb = renderRootToStringBuilder(search);
        boolean hasPredicates = false;

        StringBuilder localStringBuilder = new StringBuilder();
        if (valuesProvider != null) {
            // Render items and add the query as text to the global StringBuilder
            if (renderPredicates(search, valuesProvider.getValues(), hasPredicates, localStringBuilder)) {
                sb.append(" WHERE ");
                sb.append(localStringBuilder.toString());
            }
        } else {
            // Render items and add the query as text to the global StringBuilder
            if (renderPredicates(search, null, hasPredicates, localStringBuilder)) {
                sb.append(" WHERE ");
                sb.append(localStringBuilder.toString());
            }
        }
        renderSortings(search, sb);
        return sb.toString();
    }

    private void renderSortings(Search search, StringBuilder sb) {
        boolean isFirst = true;
        if (!CollectionUtils.isEmpty(search.getSortings())) {
            for (SearchSorting sorting : search.getSortings()) {
                if (isFirst) {
                    sb.append(" ORDER BY ").append(sorting.getAttribute()).append(JpaSortType.getForSortType(sorting.getSortType()).render());
                    isFirst = false;
                } else {
                    sb.append(", ").append(sorting.getAttribute()).append(JpaSortType.getForSortType(sorting.getSortType()).render());
                }
            }
        }
    }

    private StringBuilder renderRootToStringBuilder(Search search) {
        StringBuilder sb = new StringBuilder();
        renderProjections(search, sb);
        renderTables(search, sb);
        return sb;
    }

    private void renderProjections(Search search, StringBuilder sb) {
        sb.append("SELECT ");
        boolean isFirst = true;
        for (Projection projection : search.getProjections()) {
            String attribute = projection.getSourceToken();

            if (!projection.getAttributeName().equals("*")) {
                attribute = projection.getSourceToken() + "." + projection.getAttributeName();
            }

            if (isFirst) {
                isFirst = false;
                sb.append(attribute);
            } else {
                sb.append(", ").append(attribute);
            }
        }
    }

    private void renderTables(Search search, StringBuilder sb) {
        sb.append(" FROM ");
        boolean isFirst = true;
        for (SearchSource source : search.getSources()) {
            if (isFirst) {
                isFirst = false;
                sb.append(source.getName()).append(" ").append(source.getToken());
            } else {
                sb.append(", ").append(source.getName()).append(" ").append(source.getToken());
            }
        }
    }

    private boolean renderPredicates(Search search, Map<String, Object> values, boolean hasPredicates, StringBuilder localStringBuilder) {
        if (search.getPredicates().size() > 0 && hasMatchingParamValues(search, values)) {
            for (Predicate predicateItem : search.getPredicates()) {
                hasPredicates = renderPredicateItem(predicateItem, localStringBuilder, values, hasPredicates);
            }
        }
        return hasPredicates;
    }


    private boolean hasMatchingParamValues(Search search, Map<String, Object> paramValues) {
        boolean hasMatchingValues = false;
        for (Predicate predicateItem : search.getPredicates()) {
            if (predicateItem instanceof SinglePredicate) {
                if (((SinglePredicate) predicateItem).getValue() != null) {
                    return true;
                }
                hasMatchingValues = hasMatchingParamValue((SinglePredicate) predicateItem, paramValues);
            } else if (predicateItem instanceof GroupPredicate) {
                hasMatchingValues = hasMatchingParamValues(search.getPredicates(), paramValues);
            }
            if (hasMatchingValues) {
                return true;
            }
        }
        return false;
    }

    private boolean hasMatchingParamValues(List<Predicate> items, Map<String, Object> paramValues) {
        boolean hasMatchingValue = false;
        for (Predicate predicateItem : items) {
            if (predicateItem instanceof SinglePredicate) {
                hasMatchingValue = hasMatchingParamValue((SinglePredicate) predicateItem, paramValues);
            } else if (predicateItem instanceof GroupPredicate) {
                GroupPredicate predicateGroup = (GroupPredicate) predicateItem;
                hasMatchingValue = hasMatchingParamValues(predicateGroup.getPredicates(), paramValues);
            }
            if (hasMatchingValue) {
                return true;
            }
        }
        return false;
    }

    private boolean hasMatchingParamValue(SinglePredicate predicate, Map<String, Object> paramValues) {
        if (predicate.getValue() != null) {
            return true;
        }
        if (paramValues == null || paramValues.isEmpty()) {
            return false;
        }
        if (paramValues.containsKey(predicate.getSourceToken() + StringHelper.capitalize(predicate.getName()))) {
            return true;
        }
        return false;
    }

    private boolean renderPredicateItem(Predicate predicateItem, StringBuilder sb, Map<String, Object> paramValues, boolean hasPreDecessor) {
        if (predicateItem instanceof GroupPredicate) {
            return render((GroupPredicate) predicateItem, sb, paramValues, hasPreDecessor);
        } else {
            return render((SinglePredicate) predicateItem, sb, paramValues, hasPreDecessor);
        }
    }

    private boolean render(GroupPredicate group, StringBuilder sb, Map<String, Object> paramValues, boolean hasPreDecessor) {
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

    private boolean render(SinglePredicate item, StringBuilder sb, Map<String, Object> paramValues, boolean hasPreDecessor) {
        Object value = null;
        if (paramValues != null) {
            value = paramValues.get(item.getSourceToken() + StringHelper.capitalize(item.getName()));
        }
        if (item.getValue() != null) {
            sb.append(item.getSourceToken()).append(".").append(item.getName()).append(JpaComparatorType.getForComparatorType(item.getComparatorType()).render()).append(item.getValue());
            return true;
        } else if (value != null) {
            if (hasPreDecessor) {
                sb.append(JpaConcatType.getForConcatType(item.getConcatType()).render());
            }
            if (StringHelper.isEmpty(item.getValue())) {
                if (value instanceof String) {
                    if (StringHelper.isNotEmpty((String) value)) {
                        sb.append(item.getSourceToken()).append(".").append(item.getName()).append(JpaComparatorType.getForComparatorType(item.getComparatorType()).render()).append(":").append(item.getSourceToken()).append(StringHelper.capitalize(item.getName()));
                        return true;
                    }
                } else {
                    sb.append(item.getSourceToken()).append(".").append(item.getName()).append(JpaComparatorType.getForComparatorType(item.getComparatorType()).render()).append(":").append(item.getSourceToken()).append(StringHelper.capitalize(item.getName()));
                    return true;
                }
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see ch.alv.components.core.search.BaseSearchRendererImpl#decorateValue(java.lang.String, java.lang.Object)
     */
    @Override
    public Object decorateValue(Search search, String attributeName, Object value) {
        for (Predicate item : search.getPredicates()) {
            if (item instanceof SinglePredicate) {
                SinglePredicate predicateItem = (SinglePredicate) item;
                String name = predicateItem.getName();
                if (name.equals(attributeName)) {
                    if (value == null || (value instanceof String && StringHelper.isEmpty(value))) {
                        switch (((SinglePredicate) item).getWildcardType()) {
                            case AFTER:
                                return value + "%";
                            case BEFORE:
                                return "%" + value;
                            case AROUND:
                                return "%" + value + "%";
                        }
                    }
                }
            }
        }
        return value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) { return false; }
        if (that == this) { return true; }
        if (that.getClass() != getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 37, this);
    }

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
}
