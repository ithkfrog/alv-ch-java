package ch.alv.components.persistence.query.internal;

import java.util.ArrayList;
import java.util.List;

/**
 * Group predicates with objects of this class.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class QueryPredicateGroup implements QueryPredicateItem {

    private QueryPredicateCombination combination;

    private List<QueryPredicateItem> predicates = new ArrayList<>();

    public QueryPredicateGroup(QueryPredicateCombination combination) {
        this.combination = combination;
    }

    public QueryPredicateGroup(QueryPredicateCombination combination, List<QueryPredicateItem> predicates) {
        this.combination = combination;
        this.predicates = predicates;
    }

    public QueryPredicateGroup and(QueryPredicate predicate) {
        predicates.add(predicate);
        return this;
    }

    public QueryPredicateGroup or(QueryPredicate predicate) {
        predicates.add(predicate);
        return this;
    }

    public QueryPredicateCombination getCombination() {
        return combination;
    }

    public void setCombination(QueryPredicateCombination combination) {
        this.combination = combination;
    }

    public List<QueryPredicateItem> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<QueryPredicateItem> predicates) {
        this.predicates = predicates;
    }
}
