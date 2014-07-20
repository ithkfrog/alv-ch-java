package ch.alv.components.web.api.handler;

import ch.alv.components.web.api.TargetEntity;

/**
 * Base implementation of a DefaultRequestHandler.
 *
 * @since 1.0.0
 */
public abstract class BaseDefaultRequestHandler {

    protected Class<?> getTargetEntity(Class<?> resourceEntity) {
        if (resourceEntity.getAnnotation(TargetEntity.class) != null) {
            return resourceEntity.getAnnotation(TargetEntity.class).entityClass();
        }
        return resourceEntity;
    }

}
