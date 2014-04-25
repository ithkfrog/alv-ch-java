package ch.alv.components.core.file.flat.reader.internal;

import java.beans.Introspector;

/**
 * Metadata about specific column.
 * 
 * @since 1.0.0
 */
final class Column {

	/** name of column */
	private String name;

	/** data type of column */
	private String type;

	/** is the column a required one */
	private boolean required;

	/** name of column */
	private String dateFormat;

    /** column position in record, starting from 0 */
	private int index;

	/** should this column be skipped while parsing */
	private boolean skip;

	/** Start column position for value. Only relevant for fixed column lengths. */
	private int startColumn;

	/** End column position for value. Only relevant for fixed column lengths. */
	private int endColumn;

	/**
	 * Initializes column.
	 * 
	 * @param name the name of the column.
	 * @param type the type of the column.
	 * @param required indicates if the existence of the column/value is mandatory.
	 * @param index the position in the file.
	 * @param format the dateformat of the source value.
	 * @param skip the value is not read, if skip is true
	 */
	public Column(String name, String type, boolean required, int index,
			String format, boolean skip) {
		setName(name);
		this.type = type;
		this.required = required;
		this.index = index;
		this.dateFormat = format;
		this.skip = skip;
	}

	public int getEndColumn() {
		return endColumn;
	}

	public void setEndColumn(int end) {
		endColumn = end;
	}

	public int getStartColumn() {
		return startColumn;
	}

	public void setStartColumn(int start) {
		startColumn = start;
	}

	public String getName() {
		return name;
	}

	public void setName(String nm) {
		name = nm;
		if (Character.isUpperCase(name.charAt(0))) {
			name = Introspector.decapitalize(name);
		}
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIndex() {
		return index;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public boolean isSkip() {
		return skip;
	}
}
