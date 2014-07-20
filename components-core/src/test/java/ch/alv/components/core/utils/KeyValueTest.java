package ch.alv.components.core.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Unit tests for the {@link KeyValue} class.
 *
 * @since 1.0.0
 */
public class KeyValueTest {

    @Test
    public void testConstructor() {
        KeyValue keyValue = new KeyValue("key", Integer.valueOf(99));
        assertEquals("key", keyValue.getKey());
        assertEquals(99, keyValue.getValue());
    }

    @Test
    public void testKey() {
        KeyValue keyValue = new KeyValue();
        keyValue.setKey("key");
        assertEquals("key", keyValue.getKey());
    }

    @Test
    public void testValue() {
        KeyValue keyValue = new KeyValue();
        keyValue.setValue("value");
        assertEquals("value", keyValue.getValue());
    }
}
