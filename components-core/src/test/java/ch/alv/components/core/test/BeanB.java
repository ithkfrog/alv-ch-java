package ch.alv.components.core.test;

import ch.alv.components.core.file.flat.reader.FlatFileColumnSeparatorType;
import ch.alv.components.core.file.flat.reader.annotations.FlatFileConversion;

/**
 * Second bean for Mapper tests
 *
 * @since 1.0.0
 */
@FlatFileConversion(skipFirstLine = true, separatorType = FlatFileColumnSeparatorType.CHARACTER)
public class BeanB {

    private String key;

    private String value;

    public BeanB() {
    }

    public BeanB(String key, String value) {
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
