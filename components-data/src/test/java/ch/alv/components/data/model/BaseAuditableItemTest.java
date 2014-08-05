package ch.alv.components.data.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link BaseAuditableItem} class
 *
 * @since 1.0.0
 */
public class BaseAuditableItemTest {

    @Test
    public void testEntity() {
        User editor = new User();
        Date now = new Date();
        BaseAuditableItem entity = new BaseAuditableItem();
        entity.setCreatedBy(editor);
        assertEquals(editor, entity.getCreatedBy());
        entity.setCreatedOn(now);
        assertEquals(now, entity.getCreatedOn());
        entity.setLastUpdateBy(editor);
        assertEquals(editor, entity.getLastUpdateBy());
        entity.setLastUpdateOn(now);
        assertEquals(now, entity.getLastUpdateOn());
    }

}
