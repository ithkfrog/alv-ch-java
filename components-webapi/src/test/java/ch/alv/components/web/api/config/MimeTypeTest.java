package ch.alv.components.web.api.config;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link HeaderParameter} class.
 *
 * @since 1.0.0
 */
public class MimeTypeTest {

    @Test
    public void testDefaultConstructor() {
        assertNotNull(new MimeType());
    }

    @Test
    public void testParamConstructor() {
        assertEquals("application/json", new MimeType("application/json").getType());
    }

    @Test
    public void testType() {
        MimeType mimeType = new MimeType();
        mimeType.setType("application/json");
        assertEquals("application/json", mimeType.getType());
    }

    @Test
    public void testSchema() {
        MimeType mimeType = new MimeType();
        mimeType.setSchema("testSchema");
        assertEquals("testSchema", mimeType.getSchema());
    }

    @Test
    public void testExample() {
        MimeType mimeType = new MimeType();
        mimeType.setExample("testExample");
        assertEquals("testExample", mimeType.getExample());
    }

    @Test
    public void testFormParameters() {
        Map<String, List<FormParameter>> params = new HashMap<>();
        MimeType mimeType = new MimeType();
        mimeType.setFormParameters(params);
        assertEquals(params, mimeType.getFormParameters());
    }

    @Test
    public void testToString() {
        MimeType mimeType = new MimeType("application/json");
        assertEquals("MimeType{type='application/json'}", mimeType.toString());
    }

}
