package ch.alv.components.core.mapper;

/**
 * Created with IntelliJ IDEA.
 * User: TYWUG
 * Date: 28.02.14
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
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
