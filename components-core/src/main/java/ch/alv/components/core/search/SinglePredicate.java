package ch.alv.components.core.search;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A predicate implementation which holds a single name / value pair. If no type of predicate concatenation
 * is made, it will be added with an AND-relation.
 *
 * @since 1.0.0
 */
public class SinglePredicate extends BasePredicateImpl {

    private String sourceToken;

    private String name;

    private Object value = null;

    private ComparatorType comparatorType = ComparatorType.EQUALS;

    private WildcardType wildcardType;

    public SinglePredicate() {
        super();
    }

    public SinglePredicate(String sourceToken, String name, Object value) {
        super(ConcatType.AND);
        setSourceToken(sourceToken);
        setName(name);
        setValue(value);
    }

    public SinglePredicate(String sourceToken, String name) {
        super(ConcatType.AND);
        setSourceToken(sourceToken);
        setName(name);
    }

    public SinglePredicate(String sourceToken, String name, Object value, WildcardType wildcardType) {
        super(ConcatType.AND);
        setSourceToken(sourceToken);
        setName(name);
        setValue(value);
        setWildcardType(wildcardType);
    }

    public SinglePredicate(String sourceToken, String name, Object value, ConcatType concatType) {
        super(concatType);
        setSourceToken(sourceToken);
        setName(name);
        setValue(value);
    }

    public SinglePredicate(String sourceToken, String name, Object value, ConcatType concatType, WildcardType wildcardType) {
        super(concatType);
        setSourceToken(sourceToken);
        setName(name);
        setValue(value);
        setWildcardType(wildcardType);
    }

    public SinglePredicate(String sourceToken, String name, Object value, ComparatorType comparatorType) {
        super(ConcatType.AND);
        setSourceToken(sourceToken);
        setName(name);
        setValue(value);
        setComparatorType(comparatorType);
    }

    public SinglePredicate(String sourceToken, String name, Object value, ComparatorType comparatorType, WildcardType wildcardType) {
        super(ConcatType.AND);
        setSourceToken(sourceToken);
        setName(name);
        setValue(value);
        setComparatorType(comparatorType);
        setWildcardType(wildcardType);
    }

    public SinglePredicate(String sourceToken, String name, Object value, ConcatType concatType, ComparatorType comparatorType) {
        super(concatType);
        setSourceToken(sourceToken);
        setName(name);
        setValue(value);
        setComparatorType(comparatorType);
    }

    public SinglePredicate(String sourceToken, String name, Object value, ConcatType concatType, ComparatorType comparatorType, WildcardType wildcardType) {
        super(concatType);
        setSourceToken(sourceToken);
        setName(name);
        setValue(value);
        setComparatorType(comparatorType);
        setWildcardType(wildcardType);
    }

    public String getSourceToken() {
        return sourceToken;
    }

    public void setSourceToken(String sourceToken) {
        this.sourceToken = sourceToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ComparatorType getComparatorType() {
        return comparatorType;
    }

    public void setComparatorType(ComparatorType comparatorType) {
        this.comparatorType = comparatorType;
    }

    public WildcardType getWildcardType() {
        return wildcardType;
    }

    public void setWildcardType(WildcardType wildcardType) {
        this.wildcardType = wildcardType;
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
