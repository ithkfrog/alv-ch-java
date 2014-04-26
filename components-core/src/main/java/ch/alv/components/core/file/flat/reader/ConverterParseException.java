package ch.alv.components.core.file.flat.reader;

/**
 * Indicates that an unrecoverable parsing exception occurred while converting
 * the String to java objects.
 * 
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class ConverterParseException extends ConverterException {

    public ConverterParseException(Exception ex) {
		super(ex);
	}

	public ConverterParseException(String msg) {
		super(msg);
	}
}
