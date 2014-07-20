package ch.alv.components.web.api.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description a http response.
 *
 * @since 1.0.0
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 5492447634029702499L;

    private String description;

    private List<MimeType> body;

    private List<HeaderParameter> headers = new ArrayList<>();

    public List<HeaderParameter> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HeaderParameter> headers) {
        this.headers = headers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBody(List<MimeType> body) {
        this.body = body;
    }

    public List<MimeType> getBody() {
        return body;
    }

    public boolean hasBody() {
        return body != null && !body.isEmpty();
    }
}
