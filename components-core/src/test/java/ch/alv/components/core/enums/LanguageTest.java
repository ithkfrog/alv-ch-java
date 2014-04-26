package ch.alv.components.core.enums;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.core.enums.Language} enum.
 *
 * @since 1.0.0
 */
public class LanguageTest {

    private static final String KEY_EN = "enum.language.en";
    private static final String KEY_FR = "enum.language.fr";
    private static final String KEY_DE = "enum.language.de";
    private static final String KEY_IT = "enum.language.it";

    @Test
    public void testKeys() {
        assertEquals(KEY_EN, Language.ENGLISH.getKey());
        assertEquals(KEY_FR, Language.FRENCH.getKey());
        assertEquals(KEY_DE, Language.GERMAN.getKey());
        assertEquals(KEY_IT, Language.ITALIAN.getKey());
    }

    @Test
    public void testLocales() {
        assertEquals(Locale.ENGLISH, Language.ENGLISH.getLocale());
        assertEquals(Locale.FRENCH, Language.FRENCH.getLocale());
        assertEquals(Locale.GERMAN, Language.GERMAN.getLocale());
        assertEquals(Locale.ITALIAN, Language.ITALIAN.getLocale());
    }

    @Test
    public void testGetByCode() {
        assertEquals(Language.GERMAN, Language.getByCode("de"));
        assertEquals(Language.GERMAN, Language.getByCode("German"));
        assertEquals(null, Language.getByCode("unknownLanguage"));
    }

    @Test
    public void testCoverageOfValues() {
        assertEquals(4, Language.values().length);
    }

}
