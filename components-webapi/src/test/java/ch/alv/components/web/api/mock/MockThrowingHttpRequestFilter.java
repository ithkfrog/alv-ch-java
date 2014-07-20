package ch.alv.components.web.api.mock;

import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.filter.HttpRequestFilter;
import ch.alv.components.web.api.filter.HttpRequestFilterException;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import org.springframework.http.HttpStatus;

/**
 * Throwing mock implementation of the {@link HttpRequestFilter} interface.
 *
 * @since 1.0.0
 */
public class MockThrowingHttpRequestFilter implements HttpRequestFilter {

    private String name;

    private int priority;

    public MockThrowingHttpRequestFilter(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void filterRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws HttpRequestFilterException {
        throw new HttpRequestFilterException(name, HttpStatus.BAD_REQUEST);
    }
}
