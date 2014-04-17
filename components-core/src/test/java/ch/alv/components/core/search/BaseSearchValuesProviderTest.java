package ch.alv.components.core.search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TestCases for the {@link BaseSearchValuesProvider}.
 *
 * @since 1.0.0
 */
public class BaseSearchValuesProviderTest {

    public static final String TEST_STRING_KEY = "testStringKey";
    public static final String TEST_NULL_STRING_KEY = "testNullStringKey";
    public static final String TEST_STRING_VALUE = "testStringValue";
    public static final String TEST_BOOL_KEY = "testBoolKey";
    public static final String TEST_NULL_BOOL_KEY = "testNullBoolKey";
    public static final Boolean TEST_BOOL_VALUE = true;
    public static final String TEST_INT_KEY = "testIntKey";
    public static final String TEST_NULL_INT_KEY = "testNullIntKey";
    public static final Integer TEST_INT_VALUE = 99;
    public static final String TEST_LONG_KEY = "testLongKey";
    public static final String TEST_NULL_LONG_KEY = "testNullLongKey";
    public static final Long TEST_LONG_VALUE = Long.valueOf("101");
    public static final String TEST_FLOAT_KEY = "testFloatKey";
    public static final String TEST_NULL_FLOAT_KEY = "testNullFloatKey";
    public static final Float TEST_FLOAT_VALUE = Float.valueOf("0.00055");
    public static final String TEST_DOUBLE_KEY = "testDoubleKey";
    public static final String TEST_NULL_DOUBLE_KEY = "testNullDoubleKey";
    public static final Double TEST_DOUBLE_VALUE = Double.valueOf("0.00055");
    public static final String TEST_NULL_KEY = "testNullKey";
    public static final Object TEST_NULL_VALUE = null;
    public static final String TEST_NO_VALUE_KEY = "noValueKey";

    SearchValuesProvider provider = new TestSearchValuesProvider();

    @Test
    public void testSimpleCalls() {
        assertEquals(TEST_STRING_VALUE, provider.getStringValue(TEST_STRING_KEY));
        assertEquals(TEST_BOOL_VALUE, provider.getBooleanValue(TEST_BOOL_KEY));
        assertEquals(TEST_INT_VALUE, provider.getIntValue(TEST_INT_KEY));
        assertEquals(TEST_LONG_VALUE, provider.getLongValue(TEST_LONG_KEY));
        assertEquals(TEST_FLOAT_VALUE, provider.getFloatValue(TEST_FLOAT_KEY));
        assertEquals(TEST_DOUBLE_VALUE, provider.getDoubleValue(TEST_DOUBLE_KEY));
        assertEquals(TEST_NULL_VALUE, provider.getValue(TEST_NULL_KEY));
    }

    @Test
    public void testDefaultedCalls() {
        assertEquals(TEST_STRING_VALUE, provider.getStringValue(TEST_NO_VALUE_KEY, TEST_STRING_VALUE));
        assertEquals(TEST_BOOL_VALUE, provider.getBooleanValue(TEST_NO_VALUE_KEY, TEST_BOOL_VALUE));
        assertEquals(TEST_INT_VALUE, provider.getIntValue(TEST_NO_VALUE_KEY, TEST_INT_VALUE));
        assertEquals(TEST_LONG_VALUE, provider.getLongValue(TEST_NO_VALUE_KEY, TEST_LONG_VALUE));
        assertEquals(TEST_FLOAT_VALUE, provider.getFloatValue(TEST_NO_VALUE_KEY, TEST_FLOAT_VALUE));
        assertEquals(TEST_DOUBLE_VALUE, provider.getDoubleValue(TEST_NO_VALUE_KEY, TEST_DOUBLE_VALUE));
        assertEquals(TEST_NULL_VALUE, provider.getValue(TEST_NO_VALUE_KEY, TEST_NULL_VALUE));
    }

    @Test
    public void testSpecialCalls() {
        // inexistent keys should return null
        assertEquals(null, provider.getStringValue(TEST_NO_VALUE_KEY));
        // null keys also should return null
        assertEquals(null, provider.getStringValue(null));
    }

    public class TestSearchValuesProvider extends BaseSearchValuesProvider {

        @Override
        protected void putData() {
            values.put(TEST_STRING_KEY, TEST_STRING_VALUE);
            values.put(TEST_BOOL_KEY, TEST_BOOL_VALUE);
            values.put(TEST_INT_KEY, TEST_INT_VALUE);
            values.put(TEST_LONG_KEY, TEST_LONG_VALUE);
            values.put(TEST_FLOAT_KEY, TEST_FLOAT_VALUE);
            values.put(TEST_DOUBLE_KEY, TEST_DOUBLE_VALUE);
            values.put(TEST_NULL_KEY, TEST_NULL_VALUE);
        }
    }

}
