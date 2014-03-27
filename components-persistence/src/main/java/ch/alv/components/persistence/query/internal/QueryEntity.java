package ch.alv.components.persistence.query.internal;

/**
 * Represents an entity used in a dynamic query.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class QueryEntity {

    private Class<?> entityClass;

    private String entityToken;

    public QueryEntity(Class<?> entityClass, String entityToken) {
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

}
