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

        MockDataStoreAdapter adapter = new MockDataStoreAdapter();
        adapter.save(item, BaseModelItem.class);

        assertNotNull(adapter.find(BaseModelItem.class));
        assertEquals("testId", adapter.find(BaseModelItem.class).get(0).getId());

        assertNotNull(adapter.find(null, null, BaseModelItem.class));
        assertEquals("testId", adapter.find(null, null, BaseModelItem.class).get(0).getId());

        assertNotNull(adapter.find("testId", BaseModelItem.class));
        assertEquals("testId", adapter.find("testId", BaseModelItem.class).getId());

        adapter.delete("unknown", BaseModelItem.class);
        assertNotNull(adapter.find(BaseModelItem.class));
        assertEquals("testId", adapter.find(BaseModelItem.class).get(0).getId());

        adapter.delete("testId", BaseModelItem.class);
        assertEquals(0, adapter.find(BaseModelItem.class).size());
    }

}
