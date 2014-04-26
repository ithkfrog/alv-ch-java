package ch.alv.components.core.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link Gender} enum.
 *
 * @since 1.0.0
 */
public class GenderTest {

    private static final String KEY_FEMALE = "enums.gender.female";
    private static final String KEY_MALE = "enums.gender.male";
    private static final String KEY_UNKNOWN = "enums.gender.unknown";

    @Test
    public void testKeys() {
        assertEquals(KEY_FEMALE, Gender.FEMALE.getKey());
        assertEquals(KEY_MALE, Gender.MALE.getKey());
        assertEquals(KEY_UNKNOWN, Gender.UNKNOWN.getKey());
    }

    @Test
    public void testCoverageOfValues() {
        assertEquals(3, Gender.values().length);
    }

}
