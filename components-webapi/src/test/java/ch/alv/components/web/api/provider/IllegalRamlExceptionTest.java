package ch.alv.components.web.api.provider;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link IllegalArgumentException} class.
 *
 * @since 1.0.0
 */
public class IllegalRamlExceptionTest {

    @Test
    public void test() {
        String msg = "This raml is not legal";
        RuntimeException exception = new IllegalRamlException(msg);
        assertEquals(msg, exception.getMessage());
    }
}
