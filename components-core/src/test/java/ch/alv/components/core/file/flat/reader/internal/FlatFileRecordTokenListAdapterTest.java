package ch.alv.components.core.file.flat.reader.internal;

import ch.alv.components.core.file.flat.reader.ConverterException;
import ch.alv.components.core.test.SemicolonDelimitedBean;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test for the {@link ch.alv.components.core.file.flat.reader.internal.FlatFileBeanFactory}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class FlatFileRecordTokenListAdapterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testNullSourceInConstructor() throws IllegalAccessException, InstantiationException {
        exception.expect(ConverterException.class);
        exception.expectMessage("Source string to parse cannot be null.");
        new FlatFileRecordTokenListAdapter("", new FlatFileToObjectsConverter(SemicolonDelimitedBean.class), null);
    }

    @Test
    public void testNullConverterInConstructor() throws IllegalAccessException, InstantiationException {
        exception.expect(ConverterException.class);
        exception.expectMessage("Converter to apply must not be null.");
        new FlatFileRecordTokenListAdapter("test;string;tokens", null, SemicolonDelimitedBean.class);
    }

    @Test
    public void testNullTargetClassInConstructor() throws IllegalAccessException, InstantiationException {
        exception.expect(ConverterException.class);
        exception.expectMessage("The targetClass must not be null.");
        new FlatFileRecordTokenListAdapter("test;string;tokens", new FlatFileToObjectsConverter(SemicolonDelimitedBean.class), null);
    }


}
