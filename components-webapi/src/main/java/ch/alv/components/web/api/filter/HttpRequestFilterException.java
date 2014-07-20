package ch.alv.components.web.api.filter;

import org.springframework.http.HttpStatus;

/**
 * Exception used by failing HttpRequestFilters.
 *
 * @since 1.0.0
 */
public class HttpRequestFilterException extends Exception {

    private static final long serialVersionUID = 849971813426186069L;

    private HttpStatus status;

    public HttpRequestFilterException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
