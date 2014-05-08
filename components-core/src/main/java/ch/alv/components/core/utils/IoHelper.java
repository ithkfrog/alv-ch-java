package ch.alv.components.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Reader;

/**
 * Utility class for IO concerns.
 *
 * @since 1.0.0
 */
public class IoHelper {

    private final static Log LOG = LogFactory.getLog(IoHelper.class);

    /**
     * Close a reader without handling an eventual IOException.
     * @param reader the reader to close
     */
    public static void closeReaderQuietly(Reader reader) {
        try {
            reader.close();
        } catch (Exception e) {
            LOG.warn("Error while closing reader.", e);
        }
    }

}
