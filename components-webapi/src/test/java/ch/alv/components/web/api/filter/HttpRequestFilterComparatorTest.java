package ch.alv.components.web.api.filter;

import ch.alv.components.web.api.mock.MockHttpRequestFilter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link HttpRequestFilterComparator} class.
 *
 * @since 1.0.0
 */
public class HttpRequestFilterComparatorTest {

    private HttpRequestFilterComparator comparator = new HttpRequestFilterComparator();

    private HttpRequestFilter lowPrioFilter = new MockHttpRequestFilter("", 0);
    private HttpRequestFilter highPrioFilter = new MockHttpRequestFilter("", 1);
    private HttpRequestFilter secondHighPrioFilter = new MockHttpRequestFilter("", 1);


    @Test
    public void testPriority() {
        assertEquals(-1, comparator.compare(lowPrioFilter, highPrioFilter));
        assertEquals(1, comparator.compare(highPrioFilter, lowPrioFilter));

    }

    @Test
    public void testEquality() {
        assertEquals(0, comparator.compare(highPrioFilter, secondHighPrioFilter));
        assertEquals(0, comparator.compare(lowPrioFilter, lowPrioFilter));
    }

    @Test
    public void testNullValues() {
        assertEquals(1, comparator.compare(highPrioFilter, null));
        assertEquals(-1, comparator.compare(null, secondHighPrioFilter));
        assertEquals(0, comparator.compare(null, null));
    }

}
