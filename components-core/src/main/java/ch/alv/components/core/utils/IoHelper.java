package ch.alv.components.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * Utility class for IO concerns.
 *
 * @since 1.0.0
 */
public class IoHelper {

    private final static Log LOG = LogFactory.getLog(IoHelper.class);

    public static void closeReaderQuietly(Reader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            LOG.error("Error while closing reader.", e);
        }
    }

}
