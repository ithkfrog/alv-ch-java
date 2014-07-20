package ch.alv.components.web.api.request;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * CorrelationId carrying wrapper class for HttpServletRequests
 *
 * @since 1.0.0
 */
public class HttpServletRequestWrapper {

    private final String correlationId;

    private final HttpServletRequest request;

    public HttpServletRequestWrapper(HttpServletRequest request) {
        this.request = request;
        this.correlationId = UUID.randomUUID().toString();
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

}
