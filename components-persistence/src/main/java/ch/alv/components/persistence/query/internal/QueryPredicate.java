package ch.alv.components.persistence.query.internal;

/**
 * Predicates are used to filter the result of a query.
 *
 * @since 1.0.0
 */
public class QueryPredicate implements QueryPredicateItem {

    private String name;

    private String entityToken;

    private QueryOperator operator = QueryOperator.EQUALS;

    private QueryPredicateCombination combination;

    private Object immutableValue;

    public QueryPredicate(String name, String entityToken, QueryPredicateCombination combination) {
        this.name = name;
        this.entityToken = entityToken;
        this.combination = combination;
    }

    public QueryPredicate(String name, String entityToken, QueryPredicateCombination combination, Object immutableValue) {
        this.name = name;
        this.entityToken = entityToken;
        this.combination = combination;
        this.immutableValue = immutableValue;
    }

    public QueryPredicate(String name, String entityToken, QueryOperator operator, QueryPredicateCombination combination) {
        this.name = name;
        this.entityToken = entityToken;
        this.operator = operator;
        this.combination = combination;
    }

    public QueryPredicate(String name, String entityToken, QueryOperator operator, QueryPredicateCombination combination, Object immutableValue) {
        this.name = name;
        this.entityToken = entityToken;
        this.operator = operator;
        this.combination = combination;
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

    public QueryOperator getOperator() {
        return operator;
    }

    public void setOperator(QueryOperator operator) {
        this.operator = operator;
    }

    public QueryPredicateCombination getCombination() {
        return combination;
    }

    public void setCombination(QueryPredicateCombination combination) {
        this.combination = combination;
    }

    public Object getImmutableValue() {
        return immutableValue;
    }

    public void setImmutableValue(Object immutableValue) {
        this.immutableValue = immutableValue;
    }
}
