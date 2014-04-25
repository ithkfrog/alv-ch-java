package ch.alv.components.core.file.flat.reader.internal;

import java.util.Iterator;

interface TokenIterator extends Iterator<String> {
    
	public int getInt();

	public long getLong();

	public String getString();
}
