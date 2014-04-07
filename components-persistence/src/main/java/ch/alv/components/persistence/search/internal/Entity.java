package ch.alv.components.persistence.search.internal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents an entity used in a dynamic search.
 *
 * @since 1.0.0
 */
public class Entity {

    private Class<?> entityClass;

    private String entityToken;

    public Entity(Class<?> entityClass, String entityToken) {
        this.entityClass = entityClass;
        this.entityToken = entityToken;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public String getEntityToken() {
        return entityToken;
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

}
