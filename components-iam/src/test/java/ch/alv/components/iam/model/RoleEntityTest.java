package ch.alv.components.iam.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link Role} class
 *
 * @since 1.0.0
 */
public class RoleEntityTest {

    @Test
    public void testEntity() {
        Role entity = new Role();
        String name = "testName";
        entity.setName(name);
        assertEquals(name, entity.getName());
        assertEquals(name, entity.toString());


    }

}
