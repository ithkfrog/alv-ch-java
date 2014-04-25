package ch.alv.components.core.beans.factory;

/**
 * An implementation of this factory is responsible for creating beans.
 *
 * @since 1.0.0
 */
public interface BeanFactory {

	<T> T createBean(Class<T> targetClass);

}
