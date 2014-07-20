package ch.alv.components.web.api.mock;

import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.filter.HttpRequestFilter;
import ch.alv.components.web.api.filter.HttpRequestFilterException;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;

/**
 * Mock implementation of the {@link HttpRequestFilter} interface.
 *
 * @since 1.0.0
 */
public class MockHttpRequestFilter implements HttpRequestFilter {

    private String name;

    private int priority;

    public MockHttpRequestFilter(String name, int priority) {
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
        // do nothing
    }
}
