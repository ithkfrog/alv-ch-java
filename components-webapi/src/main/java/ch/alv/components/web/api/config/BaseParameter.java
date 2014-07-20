package ch.alv.components.web.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of a parameter.
 *
 * @since 1.0.0
 */
public abstract class BaseParameter implements Serializable {



    private String name;

    private String description;

    private ParameterType type;

    private boolean required;

    private boolean repeat;

    private List<String> enumeration = new ArrayList<>();

    private String pattern;

    private Integer minLength;

    private Integer maxLength;

    private java.math.BigDecimal minimum;

    private BigDecimal maximum;

    private String defaultValue;

    private String example;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public BaseParameter() {
        this.type = ParameterType.STRING;
    }

    public BaseParameter(String name, ParameterType type, boolean required) {
        this.name = name;
        this.type = type;
        this.required = required;
    }

    public void setName(String displayName) {
        this.name = displayName;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public ParameterType getType() {
        return type;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getExample() {
        return example;
    }

    public List<String> getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(List<String> enumeration) {
        this.enumeration = enumeration;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public BigDecimal getMaximum() {
        return maximum;
    }

    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setExample(String example) {
        this.example = example;
    }

}
