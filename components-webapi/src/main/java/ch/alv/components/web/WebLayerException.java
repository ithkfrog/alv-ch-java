package ch.alv.components.web;

import org.springframework.http.HttpStatus;

/**
 * Generic exception for web layer error handling.
 *
 * @since 1.0.0
 */
public class WebLayerException extends Exception {

    private static final long serialVersionUID = -212367508047193904L;

    private HttpStatus responseStatus;

    private Object body;

    public WebLayerException(String message, HttpStatus responseStatus) {
        super(message);
        this.responseStatus = responseStatus;
        this.body = message;
    }

    public WebLayerException(String message, Throwable cause, HttpStatus responseStatus) {
        super(message, cause);
        this.responseStatus = responseStatus;
        this.body = message;
    }

    public WebLayerException(Throwable cause, HttpStatus responseStatus) {
        super(cause);
        this.responseStatus = responseStatus;
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public Object getBody() {
        return body;
    }
}
