package ch.alv.components.data.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link BaseDateRangeAwareItem} class
 *
 * @since 1.0.0
 */
public class BaseDateRangeAwareItemTest {

    @Test
    public void testEntity() {
        Date now = new Date();
        BaseDateRangeAwareItem entity = new BaseDateRangeAwareItem();
        entity.setValidFrom(now);
        assertEquals(now, entity.getValidFrom());
        entity.setValidTo(now);
        assertEquals(now, entity.getValidTo());
    }

}
