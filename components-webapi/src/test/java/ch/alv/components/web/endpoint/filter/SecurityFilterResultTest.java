package ch.alv.components.web.endpoint.filter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link ch.alv.components.web.endpoint.filter.UnSupportedMethodException} class
 *
 * @since 1.0.0
 */
public class SecurityFilterResultTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testOKConstructor() throws UnSupportedMethodException {


        SecurityFilterResult result = new SecurityFilterResult(SecurityFilterResult.OK, "okMessage");
        assertEquals("okMessage", result.getMessage());
        assertEquals(SecurityFilterResult.OK, result.getResult());
        assertTrue(result.isOk());
    }

    @Test
    public void testNOKConstructor() throws UnSupportedMethodException {
        SecurityFilterResult result = new SecurityFilterResult(SecurityFilterResult.NOK, "nokMessage");
        assertEquals("nokMessage", result.getMessage());
        assertEquals(SecurityFilterResult.NOK, result.getResult());
        assertFalse(result.isOk());
    }



}
