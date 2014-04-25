package ch.alv.components.core.file.flat.reader;

import ch.alv.components.core.beans.factory.BeanFactory;

/**
 * Default implementation of the BeanFactory interface. The bean class must have
 * a default constructor for this to work.
 * 
 * @since 1.0.0
 */
public class DefaultBeanFactory implements BeanFactory {
	/**
	 * Creates an instance of the destination bean using its default constructor.
	 * 
	 * @param targetClass of the destination bean
	 */
	public <T> T createBean(Class<T> targetClass) {
		try {
			return targetClass.newInstance();
		} catch (Exception e) {
			throw new TransformerException(e);
		}
	}

}
