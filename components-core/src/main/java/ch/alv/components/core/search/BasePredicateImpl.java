package ch.alv.components.core.search;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Base implementation of a {@link Predicate}.
 *
 * @since 1.0.0
 */
public abstract class BasePredicateImpl implements Predicate {

    /**
     * Holds the manner how a given predicate has to be appended to its predecessor.
     */
    private ConcatType concatType = ConcatType.AND;

    /**
     * Constructor
     */
    BasePredicateImpl() {
    }

    /**
     * Constructor
     * @param concatType the manner how the predicate has to be appended to its predecessor.
     */
    BasePredicateImpl(ConcatType concatType) {
        this.concatType = concatType;
    }

    public ConcatType getConcatType() {
        return concatType;
    }

    public void setConcatType(ConcatType concatType) {
        this.concatType = concatType;
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
