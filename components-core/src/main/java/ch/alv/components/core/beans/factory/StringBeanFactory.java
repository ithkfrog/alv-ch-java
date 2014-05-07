package ch.alv.components.core.beans.factory;

import ch.alv.components.core.file.flat.reader.FlatFileObjectHandle;

import java.io.InputStream;

/**
 * Transforms a text string to a java object. Supports two modes of operation.
 * In one mode you can pass a line to the transformer and get back a java object
 * with the initialized data. In the second approach you can pass in the entire
 * file and expect to receive notifications for each line in the file (with
 * either the java object or the line itself if the transformed could not map it
 * to a java class).
 * 
 * @since 1.0.0
 */
public interface StringBeanFactory<T> {

	/**
	 * Loads a String into an instance of the specified type. In the case that
	 * the record format cannot be located a null is returned.
	 * 
	 * @param line a line, representing a record.
	 * @throws ch.alv.components.core.file.flat.reader.FlatFileConverterException if error occurs while parsing the line
	 */
	public T convert(String line);

	/**
	 * Parses the input and for every record matched, notifies the listener with
	 * the fully loaded bean. If record could not be matched then notify the
	 * listener with the record line so that it may do any custom processing.
	 *
	 * @param stream Input stream with file contents to parse
	 * @param listener listener to notify found and notfound events
	 */
	public void convert(InputStream stream, FlatFileObjectHandle<T> listener);
}