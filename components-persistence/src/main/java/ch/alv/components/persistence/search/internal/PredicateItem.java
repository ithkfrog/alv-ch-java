package ch.alv.components.persistence.search.internal;

/**
 * Predicates are used to filter the result of a query.
 *
 * @since 1.0.0
 */
public class PredicateItem implements Predicate {

    private String name;

    private String entityToken;

    private ComparatorType comparator = ComparatorType.EQUALS;

    private ConcatType concatType = ConcatType.AND;

    private Object immutableValue;

    public PredicateItem(String entityToken, String name) {
        this.name = name;
        this.entityToken = entityToken;
    }

    public PredicateItem(String entityToken, String name, Object immutableValue) {
        this.entityToken = entityToken;
        this.name = name;
        this.immutableValue = immutableValue;
    }

    public PredicateItem(String entityToken, String name, ComparatorType comparator) {
        this.entityToken = entityToken;
        this.name = name;
        this.comparator = comparator;
    }

    public PredicateItem(String entityToken, String name, ComparatorType comparator, Object immutableValue) {
        this.entityToken = entityToken;
        this.name = name;
        this.comparator = comparator;
        this.immutableValue = immutableValue;
    }

    public PredicateItem(String entityToken, String name, ConcatType concatType) {
        this.entityToken = entityToken;
        this.name = name;
        this.concatType = concatType;
    }

    public PredicateItem(String entityToken, String name, ConcatType concatType, Object immutableValue) {
        this.entityToken = entityToken;
        this.name = name;
        this.concatType = concatType;
        this.immutableValue = immutableValue;
    }

    public PredicateItem(String entityToken, String name, ComparatorType comparator, ConcatType concatType) {
        this.entityToken = entityToken;
        this.name = name;
        this.comparator = comparator;
        this.concatType = concatType;
    }

    public PredicateItem(String entityToken, String name, ComparatorType comparator, ConcatType concatType, Object immutableValue) {
        this.entityToken = entityToken;
        this.name = name;
        this.comparator = comparator;
        this.concatType = concatType;
        this.immutableValue = immutableValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityToken() {
        return entityToken;
    }

    public void setEntityToken(String entityToken) {
        this.entityToken = entityToken;
    }

    public ComparatorType getComparator() {
        return comparator;
    }

    public void setComparator(ComparatorType comparator) {
        this.comparator = comparator;
    }

    public ConcatType getConcatType() {
        return concatType;
    }

    public void setConcatType(ConcatType concatType) {
        this.concatType = concatType;
    }

    public Object getImmutableValue() {
        return immutableValue;
    }

    public void setImmutableValue(Object immutableValue) {
        this.immutableValue = immutableValue;
    }
}
