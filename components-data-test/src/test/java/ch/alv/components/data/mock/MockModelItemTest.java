package ch.alv.components.data.mock;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.data.mock.MockModelItem} class
 *
 * @since 1.0.0
 */
public class MockModelItemTest {

    @Test
    public void testConstructors() {
        MockModelItem item = new MockModelItem("testName");
        assertEquals("testName", item.getName());
        item = new MockModelItem(3, "testName");
        assertEquals("testName", item.getName());
        assertEquals(Integer.valueOf(3), item.getVersion());
        item = new MockModelItem("testId", 3, "testName");
        assertEquals("testId", item.getId());
        assertEquals("testName", item.getName());
        assertEquals(Integer.valueOf(3), item.getVersion());
    }

    @Test
    public void testGettersAndSetters() {
        MockModelItem item = new MockModelItem();
        item.setName("testName");
        assertEquals("testName", item.getName());
    }

}
