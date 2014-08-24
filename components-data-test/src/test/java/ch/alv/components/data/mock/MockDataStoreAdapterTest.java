package ch.alv.components.data.mock;

import ch.alv.components.data.model.BaseModelItem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test cases for the {@link MockDataStoreAdapter} class
 *
 * @since 1.0.0
 */
public class MockDataStoreAdapterTest {

    @Test
    public void testCRUD() {
        MockModelItem item = new MockModelItem();
        item.setId("testId");

        MockDataStoreAdapter<MockModelItem> adapter = new MockDataStoreAdapter<>();
        adapter.save(item, MockModelItem.class);

        assertNotNull(adapter.find(MockModelItem.class));
        assertEquals("testId", adapter.find(MockModelItem.class).get(0).getId());

        assertNotNull(adapter.find(null, null, MockModelItem.class));
        assertEquals("testId", adapter.find(null, null, MockModelItem.class).get(0).getId());

        assertNotNull(adapter.find("testId", MockModelItem.class));
        assertEquals("testId", adapter.find("testId", MockModelItem.class).getId());

        adapter.delete("unknown", MockModelItem.class);
        assertNotNull(adapter.find(MockModelItem.class));
        assertEquals("testId", adapter.find(MockModelItem.class).get(0).getId());

        adapter.delete("testId", MockModelItem.class);
        assertEquals(0, adapter.find(MockModelItem.class).size());
    }

}
