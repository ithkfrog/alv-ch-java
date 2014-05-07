package ch.alv.components.web.controller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test for the {@link ch.alv.components.core.file.flat.reader.internal.FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
public class NoSuchValuesProviderExceptionTest {

    private static final String MSG = "testMessage";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testWithStringParam() throws NoSuchValuesProviderException {
        exception.expect(NoSuchValuesProviderException.class);
        exception.expectMessage(MSG);
        throw new NoSuchValuesProviderException(MSG);
    }

}
