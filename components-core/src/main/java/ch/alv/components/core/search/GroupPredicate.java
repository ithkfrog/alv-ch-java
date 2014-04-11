package ch.alv.components.core.search;

import ch.alv.components.core.utils.StringHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A predicate implementation which holds a single name / value pair. If no type of predicate concatenation
 * is made, it will be added with an AND-relation.
 *
 * @since 1.0.0
 */
public class GroupPredicate extends BasePredicateImpl {

    private List<Predicate> predicates = new ArrayList<>();

    public GroupPredicate() {
        super(ConcatType.AND);
    }

    public GroupPredicate(ConcatType concatType) {
        super(concatType);
    }

    public GroupPredicate(List<Predicate> predicates) {
        super(ConcatType.AND);
        setPredicates(predicates);
    }

    public GroupPredicate(ConcatType concatType, List<Predicate> predicates) {
        super(concatType);
        setPredicates(predicates);
    }

    public GroupPredicate and(String sourceToken, String attributeName) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        getPredicates().add(new SinglePredicate(sourceToken, attributeName, null, ConcatType.AND));
        return this;
    }

    public GroupPredicate and(String sourceToken, String attributeName, Object value) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        getPredicates().add(new SinglePredicate(sourceToken, attributeName, value, ConcatType.AND));
        return this;
    }

    public GroupPredicate and(String sourceToken, String attributeName, Object value, ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        if (comparatorType == null) {
            throw new IllegalStateException("Parameter 'comparatorType' must not be null.");
        }
        getPredicates().add(new SinglePredicate(sourceToken, attributeName, value, ConcatType.AND, comparatorType));
        return this;
    }

    public GroupPredicate and(String sourceToken, String attributeName, ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        if (comparatorType == null) {
            throw new IllegalStateException("Parameter 'comparatorType' must not be null.");
        }
        getPredicates().add(new SinglePredicate(sourceToken, attributeName, null, ConcatType.AND, comparatorType));
        return this;
    }

    public GroupPredicate and(List<Predicate> predicates) {
        if (CollectionUtils.isEmpty(predicates)) {
            throw new IllegalStateException("Parameter 'predicates' must not be null.");
        }
        GroupPredicate group = new GroupPredicate();
        group.setConcatType(ConcatType.AND);
        group.getPredicates().addAll(predicates);
        getPredicates().add(group);
        return this;
    }

    public GroupPredicate and(GroupPredicate group) {
        if (group == null) {
            throw new IllegalStateException("Parameter 'group' must not be null.");
        }
        group.setConcatType(ConcatType.AND);
        getPredicates().add(group);
        return this;
    }

    public GroupPredicate or(String sourceToken, String attributeName) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        getPredicates().add(new SinglePredicate(sourceToken, attributeName, null, ConcatType.OR));
        return this;
    }

    public GroupPredicate or(String sourceToken, String attributeName, Object value) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        getPredicates().add(new SinglePredicate(sourceToken, attributeName, value, ConcatType.OR));
        return this;
    }

    public GroupPredicate or(String sourceToken, String attributeName,ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        if (comparatorType == null) {
            throw new IllegalStateException("Parameter 'comparatorType' must not be null.");
        }
        getPredicates().add(new SinglePredicate(sourceToken, attributeName, null, ConcatType.OR, ComparatorType.EQUALS));
        return this;
    }

    public GroupPredicate or(String sourceToken, String attributeName, Object value, ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        if (comparatorType == null) {
            throw new IllegalStateException("Parameter 'comparatorType' must not be null.");
        }
        getPredicates().add(new SinglePredicate(sourceToken, attributeName, value, ConcatType.OR, ComparatorType.EQUALS));
        return this;
    }

    public GroupPredicate or(List<Predicate> predicates) {
        if (CollectionUtils.isEmpty(predicates)) {
            throw new IllegalStateException("Parameter 'predicates' must not be null.");
        }
        GroupPredicate group = new GroupPredicate();
        group.setConcatType(ConcatType.OR);
        group.getPredicates().addAll(predicates);
        getPredicates().add(group);
        return this;
    }

    public GroupPredicate or(GroupPredicate group) {
        if (group == null) {
            throw new IllegalStateException("Parameter 'group' must not be null.");
        }
        group.setConcatType(ConcatType.OR);
        getPredicates().add(group);
        return this;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object that) {
        return that != null && (that == this || that.getClass() == getClass() && EqualsBuilder.reflectionEquals(this, that));
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 37, this);
    }
}
