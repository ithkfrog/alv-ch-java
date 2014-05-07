package ch.alv.components.iam.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link Permission} class
 *
 * @since 1.0.0
 */
public class PermissionEntityTest {

    @Test
    public void testEntity() {
        Permission entity = new Permission();

        String name = "testName";
        String key = "testKey";

        entity.setName(name);
        entity.setKey(key);

        assertEquals(name, entity.getName());
        assertEquals(key, entity.getKey());
    }

}
