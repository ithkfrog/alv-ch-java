package ch.alv.components.iam.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link Module} class
 *
 * @since 1.0.0
 */
public class ModuleEntityTest {

    @Test
    public void testEntity() {
        Module entity = new Module();
        String name = "testName";
        entity.setName(name);
        assertEquals(name, entity.getName());
    }

}
