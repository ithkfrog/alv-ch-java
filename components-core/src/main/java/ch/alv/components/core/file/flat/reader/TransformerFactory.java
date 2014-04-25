package ch.alv.components.core.file.flat.reader;

import ch.alv.components.core.file.flat.reader.internal.FlatFileTransformer;

/**
 * Factory class used to initialize and retrieve an instance of the
 * <code>Transformer</code>.
 * 
 * @since 1.0.0
 */
public class TransformerFactory {
	/**
	 * Returns an initialized <code>Transformer</code> instance.
	 * 
	 * @param clazz array with classes annotated with
	 * @return transformer instance
	 */
    @SuppressWarnings("unchecked")
	public static Transformer getTransformer(Class clazz) {
		return new FlatFileTransformer(clazz);
	}
}
