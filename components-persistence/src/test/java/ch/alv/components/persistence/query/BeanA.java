package ch.alv.components.persistence.query;

/**
 * TestBean for the QueryRendererTest
 *
 * @since 1.0.0
 */
public class BeanA {

    private String key;

    private String value;

    public BeanA() {
    }

    public BeanA(String key, String value) {
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
