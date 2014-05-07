package ch.alv.components.data.jpa;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link BaseJpaModelItem} class
 *
 * @since 1.0.0
 */
public class BaseJpaModelItemTest {

    @Test
    public void testEntity() {
        BaseJpaModelItem entity = new BaseJpaModelItem();
        String id = "testId";
        int version = 99;
        entity.setId(id);
        entity.setVersion(version);
        assertEquals(id, entity.getId());
        assertEquals((Integer) version, entity.getVersion());
    }

}
