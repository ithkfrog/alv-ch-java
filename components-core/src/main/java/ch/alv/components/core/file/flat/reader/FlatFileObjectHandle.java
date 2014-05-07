package ch.alv.components.core.file.flat.reader;

/**
 * Register an implementation of this handle to be called on an object.
 * 
 * @since 1.0.0
 */
public interface FlatFileObjectHandle<T> {

	/**
	 * Method to execute on the object.
	 * 
	 * @param object object to treat
	 * @return return true to continue, false to abort the current computation (loop).
	 */
	public boolean handle(T object);

}
