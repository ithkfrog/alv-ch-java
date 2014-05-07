package ch.alv.components.core.file.flat.reader.internal;

import ch.alv.components.core.beans.factory.BeanFactory;
import ch.alv.components.core.file.flat.reader.FlatFileConverterException;

/**
 * Default implementation of the BeanFactory interface. The bean class must have
 * a default constructor for this to work.
 * 
 * @since 1.0.0
 */
public class FlatFileBeanFactory implements BeanFactory {
	/**
	 * Creates an instance of the destination bean using its default constructor.
	 * 
	 * @param targetClass of the destination bean
	 */
	public <T> T createBean(Class<T> targetClass) {
		try {
			return targetClass.newInstance();
		} catch (Exception e) {
			throw new FlatFileConverterException(e);
		}
	}

}
