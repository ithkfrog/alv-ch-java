package ch.alv.components.data.model;

import ch.alv.components.core.enums.Language;

/**
 * Thrown if the database contains no matching entry.
 *
 * @since 1.0.0
 */
public class NoSuchTextConstantException extends RuntimeException {

    private static final long serialVersionUID = 8465599279971433977L;

    public NoSuchTextConstantException(String key, Language language) {
        super("Cound not find textConstant for key '" + key + "' and language '" + language.toString() + "'.");
    }
}
