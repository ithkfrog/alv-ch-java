package ch.alv.components.data.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a history of entities must be created and what entity is dedicated to do
 * the historizing job.
 *
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Historised {

    Class<? extends HistorisingEntity> targetClass();

}
