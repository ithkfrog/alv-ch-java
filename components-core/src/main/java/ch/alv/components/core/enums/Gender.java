package ch.alv.components.core.enums;

/**
 * Gender enum
 *
 * @since 1.0.0
 */
public enum Gender {

    FEMALE("enums.gender.female"),
    MALE("enums.gender.male"),
    UNKNOWN("enums.gender.unknown");

    private String key;

    private Gender(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
