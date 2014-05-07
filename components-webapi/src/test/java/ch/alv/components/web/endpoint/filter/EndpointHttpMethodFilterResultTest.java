package ch.alv.components.web.endpoint.filter;

import ch.alv.components.web.endpoint.filter.internal.EndpointHttpMethodFilterResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link UnSupportedMethodException} class
 *
 * @since 1.0.0
 */
public class EndpointHttpMethodFilterResultTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test() throws UnSupportedMethodException {
        EndpointHttpMethodFilterResult result = new EndpointHttpMethodFilterResult(EndpointHttpMethodFilterResult.OK, "okMessage");
        assertEquals("okMessage", result.getMessage());
        assertEquals(EndpointHttpMethodFilterResult.OK, result.getResult());
        assertTrue(result.isOk());
    }

    @Test
    public void testNOKConstructor() throws UnSupportedMethodException {
        EndpointHttpMethodFilterResult result = new EndpointHttpMethodFilterResult(EndpointHttpMethodFilterResult.NOK, "nokMessage");
        assertEquals("nokMessage", result.getMessage());
        assertEquals(EndpointHttpMethodFilterResult.NOK, result.getResult());
        assertFalse(result.isOk());
    }



}
