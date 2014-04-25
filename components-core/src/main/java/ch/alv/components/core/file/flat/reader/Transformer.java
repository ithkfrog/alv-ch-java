package ch.alv.components.core.file.flat.reader;

import java.io.File;
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
public interface Transformer {
	enum ColumnSeparator {
		CHARACTER, FIXLENGTH
	}

	/**
	 * Loads the record into an instance of the specified type. In the case that
	 * the record format cannot be located a null is returned.
	 * 
	 * @param line a line of the file, representing a record.
	 * @throws TransformerException if error occurs while parsing the line
	 */
	public Object loadRecord(String line);

	/**
	 * Parses the file and for every record matched, notifies the listener with
	 * the fully loaded bean. If record could not be matched then notify the
	 * listener with the record line so that it may do any custom processing.
	 * 
	 * @param file file to parse
	 * @param listener listener to notify found and notfound events
	 */
	public void parseFlatFile(File file, RecordListener listener);

	/**
	 * Parses the file and for every record matched, notifies the listener with
	 * the fully loaded bean. If record could not be matched then notify the
	 * listener with the record line so that it may do any custom processing.
	 * 
	 * @param stream Input stream with file contents to parse
	 * @param listener listener to notify found and notfound events
	 */
	public void parseFlatFile(InputStream stream, RecordListener listener);
}