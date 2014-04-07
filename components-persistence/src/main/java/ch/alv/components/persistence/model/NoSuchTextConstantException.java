package ch.alv.components.persistence.model;

import ch.alv.components.core.enums.Language;

/**
 * Thrown if the database contains no matching entry.
 *
 * @since 1.0.0
 */
public class NoSuchTextConstantException extends RuntimeException {
    public NoSuchTextConstantException(String key, Language language) {
        super("Cound not find textConstant for key '" + key + "' and language '" + language.toString() + "'.");
    }
}
