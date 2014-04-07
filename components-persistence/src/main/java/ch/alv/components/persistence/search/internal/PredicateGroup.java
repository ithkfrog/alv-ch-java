package ch.alv.components.persistence.search.internal;

import java.util.ArrayList;
import java.util.List;

/**
 * Group predicates with objects of this class.
 *
 * @since 1.0.0
 */
public class PredicateGroup implements Predicate {

    private ConcatType concatType = ConcatType.AND;

    private List<Predicate> predicates = new ArrayList<>();

    public PredicateGroup() {
    }

    public PredicateGroup(ConcatType concatType) {
        this.concatType = concatType;
    }

    public PredicateGroup(ConcatType concatType, List<Predicate> predicates) {
        this.concatType = concatType;
        this.predicates = predicates;
    }

    public PredicateGroup and(String entityToken, String attributeName) {
        predicates.add(new PredicateItem(entityToken, attributeName, ConcatType.AND));
        return this;
    }

    public PredicateGroup and(String entityToken, String attributeName, Object immutableValue) {
        predicates.add(new PredicateItem(entityToken, attributeName, ConcatType.AND, immutableValue));
        return this;
    }

    public PredicateGroup and(String entityToken, String attributeName, ComparatorType comparatorType) {
        predicates.add(new PredicateItem(entityToken, attributeName, comparatorType, ConcatType.AND));
        return this;
    }

    public PredicateGroup and(String entityToken, String attributeName, ComparatorType comparatorType, Object immutableValue) {
        predicates.add(new PredicateItem(entityToken, attributeName, comparatorType, ConcatType.AND, immutableValue));
        return this;
    }

    public PredicateGroup and(List<Predicate> predicates) {
        this.predicates.add(new PredicateGroup(ConcatType.AND, predicates));
        return this;
    }

    public PredicateGroup and(PredicateGroup group) {
        predicates.add(group);
        return this;
    }

    public PredicateGroup or(String entityToken, String attributeName) {
        predicates.add(new PredicateItem(entityToken, attributeName, ConcatType.OR));
        return this;
    }

    public PredicateGroup or(String entityToken, String attributeName, Object immutableValue) {
        predicates.add(new PredicateItem(entityToken, attributeName, ConcatType.OR, immutableValue));
        return this;
    }

    public PredicateGroup or(String entityToken, String attributeName, ComparatorType comparatorType) {
        predicates.add(new PredicateItem(entityToken, attributeName, comparatorType, ConcatType.OR));
        return this;
    }

    public PredicateGroup or(String entityToken, String attributeName, ComparatorType comparatorType, Object immutableValue) {
        predicates.add(new PredicateItem(entityToken, attributeName, comparatorType, ConcatType.OR, immutableValue));
        return this;
    }

    public PredicateGroup or(List<Predicate> predicates) {
        predicates.add(new PredicateGroup(ConcatType.OR, predicates));
        return this;
    }

    public PredicateGroup or(PredicateGroup group) {
        predicates.add(group);
        return this;
    }

    public ConcatType getConcatType() {
        return concatType;
    }

    public void setConcatType(ConcatType concatType) {
        this.concatType = concatType;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }
}
