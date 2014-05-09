package ch.alv.components.web.search.internal;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link ch.alv.components.web.search.internal.RequestParamsToValuesMapperHelper} class.
 *
 * @since 1.0.0
 */
public class RequestParamsToValuesMapperHelperTest {

    private RequestParamsToValuesMapperHelper helper = new RequestParamsToValuesMapperHelper();

    private Map<String, String[]> source = new HashMap<>();

    @Before
    public void init() {
        source.put("stringKey", new String[]{"stringValue"});
        source.put("multiValueKey", new String[]{"multiValue1", "multiValue2"});
        source.put("intKey", new String[]{"99"});
        source.put("floatKey", new String[]{"11.3256"});
        source.put("booleanKey", new String[]{"true"});
        source.put("emptyKey", new String[]{});
    }

    @Test
    public void testGetSingleSourceValue() {
        assertEquals("stringValue", helper.getSingleSourceValue("stringKey", source, String.class));
        assertEquals(Integer.valueOf("99"), helper.getSingleSourceValue("intKey", source, Integer.class));
        assertEquals(Long.valueOf("99"), helper.getSingleSourceValue("intKey", source, Long.class));
        assertEquals(Float.valueOf("11.3256"), helper.getSingleSourceValue("floatKey", source, Float.class));
        assertEquals(Double.valueOf("11.3256"), helper.getSingleSourceValue("floatKey", source, Double.class));
        assertTrue(helper.getSingleSourceValue("booleanKey", source, Boolean.class));
        assertNull(helper.getSingleSourceValue("unknownKey", source, Boolean.class));
        assertNull(helper.getSingleSourceValue("emptyKey", source, Boolean.class));
        assertNull(helper.getSingleSourceValue("stringValue", null, String.class));
    }

    @Test
    public void testGetStringSourceValue() {
        assertEquals("stringValue", helper.getStringSourceValue("stringKey", source));
        assertNull(helper.getStringSourceValue("unknownKey", source));
    }

    @Test
    public void testGetMultiSourceValue() {
        List<String> items = new ArrayList<>();
        items.add("multiValue1");
        items.add("multiValue2");
        assertEquals(items, helper.getMultiSourceValue("multiValueKey", source, String.class));
        assertNull(helper.getMultiSourceValue("unknownKey", source, String.class));
        assertNull(helper.getMultiSourceValue("stringKey", source, null));
        assertNull(helper.getMultiSourceValue("stringKey", null, String.class));
        assertNull(helper.getMultiSourceValue("emptyKey", source, String.class));
    }


}
