package ch.alv.components.core.mock;

/**
 * Class for test purposes.
 *
 * @since 1.0.0
 */
public class MockBeanA {

    private String key;

    private String value;

    public MockBeanA() {
    }

    public MockBeanA(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
