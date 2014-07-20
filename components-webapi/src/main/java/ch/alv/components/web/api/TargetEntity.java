package ch.alv.components.web.api;

/**
 * Marks a relation to a persisted entity type.
 *
 * @since 1.0.0
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface TargetEntity {

    Class<?> entityClass();
}
