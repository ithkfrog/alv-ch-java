package ch.alv.components.web.dto;

import ch.alv.components.web.dto.internal.TranslatedText;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.web.dto.internal.TranslatedText} class
 *
 * @since 1.0.0
 */
public class TranslatedTextTest {

    @Test
    public void testDto() {
        TranslatedText text = new TranslatedText("testText");
        assertEquals("testText", text.getText());
        text.setText("newText");
        assertEquals("newText", text.getText());
    }

}
