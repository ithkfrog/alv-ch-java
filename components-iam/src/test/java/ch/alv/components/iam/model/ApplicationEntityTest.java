package ch.alv.components.iam.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link Application} class
 *
 * @since 1.0.0
 */
public class ApplicationEntityTest {

    @Test
    public void testEntity() {
        Application entity = new Application();

        String name = "testName";
        entity.setName(name);
        assertEquals(name, entity.getName());
    }

}
