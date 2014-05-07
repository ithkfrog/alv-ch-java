package ch.alv.components.data.jpa;

import ch.alv.components.core.enums.Language;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test for the {@link ch.alv.components.core.file.flat.reader.internal.FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
public class NoSuchTextConstantExceptionTest {

    private static final String MSG = "Cound not find textConstant for key 'property.key.test' and language 'GERMAN'.";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testWithStringParam() {
        exception.expect(NoSuchTextConstantException.class);
        exception.expectMessage(MSG);
        throw new NoSuchTextConstantException("property.key.test", Language.GERMAN);
    }

}
