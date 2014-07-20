package ch.alv.components.web.api.utils;

import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import com.google.common.io.CharStreams;
import org.springframework.http.HttpStatus;

/**
 * Utility class for request handlers.
 *
 * @since 1.0.0
 */
public class RequestHandlerUtils {

    public static String getRequestBodyAsString(HttpServletRequestWrapper wrapper) throws WebLayerException {
        try {
            return CharStreams.toString(wrapper.getRequest().getReader());
        } catch (Exception e) {
            throw new WebLayerException("Could not successfully read request body.", HttpStatus.BAD_REQUEST);
        }
    }

}
