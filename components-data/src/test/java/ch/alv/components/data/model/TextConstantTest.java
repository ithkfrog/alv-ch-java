package ch.alv.components.data.model;

import ch.alv.components.core.enums.Language;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.data.model.TextConstant} class
 *
 * @since 1.0.0
 */
public class TextConstantTest {

    @Test
    public void testEntity() {
        TextConstant entity = new TextConstant();
        entity.setLanguage(Language.FRENCH);
        entity.setText("testText");
        assertEquals("testText", entity.getText());
        assertEquals(Language.FRENCH, entity.getLanguage());
    }

}
