package ch.alv.components.core.search;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * TestCases for the {@link ch.alv.components.core.search.MapBasedValuesProvider}.
 *
 * @since 1.0.0
 */
public class MapBasedValuesProviderTest {

    public static final String TEST_STRING_KEY = "testStringKey";
    public static final String TEST_NULL_STRING_KEY = "testNullStringKey";
    public static final String TEST_STRING_VALUE = "testStringValue";
    public static final String TEST_BOOL_KEY = "testBoolKey";
    public static final String TEST_NULL_BOOL_KEY = "testNullBoolKey";
    public static final String TEST_BOOL_VALUE = "true";
    public static final String TEST_INT_KEY = "testIntKey";
    public static final String TEST_NULL_INT_KEY = "testNullIntKey";
    public static final String TEST_INT_VALUE = "99";
    public static final String TEST_LONG_KEY = "testLongKey";
    public static final String TEST_NULL_LONG_KEY = "testNullLongKey";
    public static final String TEST_LONG_VALUE = "101";
    public static final String TEST_FLOAT_KEY = "testFloatKey";
    public static final String TEST_NULL_FLOAT_KEY = "testNullFloatKey";
    public static final String TEST_FLOAT_VALUE = "0.00055";
    public static final String TEST_DOUBLE_KEY = "testDoubleKey";
    public static final String TEST_NULL_DOUBLE_KEY = "testNullDoubleKey";
    public static final String TEST_DOUBLE_VALUE = "0.00055";
    public static final String TEST_NULL_KEY = "testNullKey";
    public static final String TEST_NULL_VALUE = null;
    public static final String TEST_NO_VALUE_KEY = "noValueKey";
    public static final String TEST_NULL_ARRAY_KEY = "nullArrayKey";
    public static final String TEST_EMPTY_ARRAY_KEY = "emptyArrayKey";
    public static final String TEST_MULTI_ARRAY_KEY = "multiArrayKey";
    public static final String TEST_EMPTY_STRING_KEY = "emptyStringKey";
    public static final String TEST_EMPTY_STRING_VALUE = "";

    public static final String TEST_DEFAULT_VALUE = "defaultValue";

    private final Map<String, String[]> values = new HashMap<>();

    private ValuesProvider provider;

    @Before
    public void init() {
        values.clear();
        values.put(TEST_STRING_KEY, new String[]{TEST_STRING_VALUE});
        values.put(TEST_BOOL_KEY, new String[] {TEST_BOOL_VALUE});
        values.put(TEST_INT_KEY, new String[] {TEST_INT_VALUE});
        values.put(TEST_LONG_KEY, new String[] {TEST_LONG_VALUE});
        values.put(TEST_FLOAT_KEY, new String[] {TEST_FLOAT_VALUE});
        values.put(TEST_DOUBLE_KEY, new String[] {TEST_DOUBLE_VALUE});
        values.put(TEST_NULL_KEY, new String[] {TEST_NULL_VALUE});
        values.put(TEST_EMPTY_STRING_KEY, new String[] {TEST_EMPTY_STRING_VALUE});
        values.put(TEST_EMPTY_ARRAY_KEY, new String[] {});
        values.put(TEST_NULL_ARRAY_KEY, null);
        values.put(TEST_MULTI_ARRAY_KEY, new String[] { TEST_STRING_VALUE, TEST_BOOL_VALUE, TEST_INT_VALUE });
        provider = new MapBasedValuesProvider(values);
    }


    @Test
    public void testDefaultConstructor() {
        MapBasedValuesProvider provider = new MapBasedValuesProvider();
        assertNotNull(provider);
    }

    @Test
    public void testParamConstructor() {
        MapBasedValuesProvider provider = new MapBasedValuesProvider(values);
        assertEquals(11, provider.getValues().size());
    }

    @Test
    public void testSetValues() {
        MapBasedValuesProvider provider = new MapBasedValuesProvider();
        provider.setValues(values);
        assertEquals(11, provider.getValues().size());

        provider = new MapBasedValuesProvider();
        provider.setValues(null);
        assertEquals(0, provider.getValues().size());
    }

    @Test
    public void testGetStringValue() {
        assertEquals(TEST_STRING_VALUE, provider.getStringValue(TEST_STRING_KEY));
    }

    @Test
    public void testGetSingleValue() {
        // default path
        assertEquals(TEST_STRING_VALUE, provider.getStringValue(TEST_STRING_KEY));
        assertEquals(Boolean.valueOf(TEST_BOOL_VALUE), provider.getSingleValue(TEST_BOOL_KEY, Boolean.class));
        assertEquals(Integer.valueOf(TEST_INT_VALUE), provider.getSingleValue(TEST_INT_KEY, Integer.class));
        assertEquals(Long.valueOf(TEST_LONG_VALUE), provider.getSingleValue(TEST_LONG_KEY, Long.class));
        assertEquals(Float.valueOf(TEST_FLOAT_VALUE), provider.getSingleValue(TEST_FLOAT_KEY, Float.class));
        assertEquals(Double.valueOf(TEST_DOUBLE_VALUE), provider.getSingleValue(TEST_DOUBLE_KEY, Double.class));
        assertNull(provider.getSingleValue(TEST_NULL_KEY, String.class));

        // special paths
        assertNull(provider.getSingleValue(TEST_NO_VALUE_KEY, String.class));
        assertNull(provider.getSingleValue(TEST_EMPTY_ARRAY_KEY, String.class));
        assertNull(provider.getSingleValue(TEST_NULL_ARRAY_KEY, String.class));
    }

    @Test
    public void testGetMultiValue() {
        // default path
        List<String> list = provider.getMultiValue(TEST_MULTI_ARRAY_KEY, String.class);
        assertEquals(3, list.size());

        // special paths
        assertNull(provider.getMultiValue(TEST_NO_VALUE_KEY, String.class));
        assertNull(provider.getMultiValue(TEST_EMPTY_ARRAY_KEY, null));
        assertNull(provider.getMultiValue(TEST_EMPTY_ARRAY_KEY, String.class));
    }

}
