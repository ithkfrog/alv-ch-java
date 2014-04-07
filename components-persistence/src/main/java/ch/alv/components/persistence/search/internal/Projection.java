package ch.alv.components.persistence.search.internal;

import ch.alv.components.core.utils.StringHelper;

/**
 * Define which attributes are selected.
 *
 * @since 1.0.0
 */
public class Projection {

    private Class<?> entityClass;

    private String entityToken;

    private String attributeName;

    public Projection(String entityToken, Class<?> entityClass, String attributeName) {
        this.entityToken = entityToken;
        this.entityClass = entityClass;
        this.attributeName = attributeName;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public String getEntityToken() {
        return StringHelper.uncapitalize(entityClass.getSimpleName());
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
