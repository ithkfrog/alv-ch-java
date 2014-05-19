package ch.alv.components.data.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.data.model.BaseModelItem} class
 *
 * @since 1.0.0
 */
public class BaseModelItemTest {

    @Test
    public void testEntity() {
        BaseModelItem entity = new BaseModelItem();
        String id = "testId";
        int version = 99;
        entity.setId(id);
        entity.setVersion(version);
        assertEquals(id, entity.getId());
        assertEquals((Integer) version, entity.getVersion());
    }

}
