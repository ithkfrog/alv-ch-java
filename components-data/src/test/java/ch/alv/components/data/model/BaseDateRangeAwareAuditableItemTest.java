package ch.alv.components.data.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.data.model.BaseDateRangeAwareAuditableItem} class
 *
 * @since 1.0.0
 */
public class BaseDateRangeAwareAuditableItemTest {

    @Test
    public void testEntity() {
        Date now = new Date();
        BaseDateRangeAwareAuditableItem entity = new BaseDateRangeAwareAuditableItem();
        entity.setValidFrom(now);
        assertEquals(now, entity.getValidFrom());
        entity.setValidTo(now);
        assertEquals(now, entity.getValidTo());
    }

}
