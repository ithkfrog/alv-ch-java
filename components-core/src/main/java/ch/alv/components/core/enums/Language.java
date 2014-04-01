package ch.alv.components.core.enums;

import java.util.Locale;

/**
 * Wrapper Enum to persist Locale data.
 *
 * @since 1.0.0
 */
public enum Language {

    ENGLISH("enum.language.en", Locale.ENGLISH),
    FRENCH("enum.language.fr", Locale.FRENCH),
    GERMAN("enum.language.de", Locale.GERMAN),
    ITALIAN("enum.language.it", Locale.ITALIAN);

    private String key;

    private Locale locale;

    private Language(String key, Locale locale) {
        this.key = key;
        this.locale = locale;
    }

    public String getKey() {
        return key;
    }

    public Locale getLocale() {
        return locale;
    }
}
