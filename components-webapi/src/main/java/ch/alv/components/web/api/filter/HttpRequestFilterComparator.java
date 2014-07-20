package ch.alv.components.web.api.filter;

import java.util.Comparator;

/**
 * Comparator implementation for {@link HttpRequestFilter} instances.
 *
 * @since 1.0.0
 */
public class HttpRequestFilterComparator implements Comparator<HttpRequestFilter> {

    @Override
    public int compare(HttpRequestFilter o1, HttpRequestFilter o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        }
        return Integer.valueOf(o1.getPriority()).compareTo(o2.getPriority());
    }

}