package ch.alv.components.web.api.config;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Description of a Mimetype.
 *
 * @since 1.0.0
 */
public class MimeType implements Serializable {

    private static final long serialVersionUID = 6485154654435841038L;

    private String type;

    private String schema;

    private String example;

    private java.util.Map<String, List<FormParameter>> formParameters;

    public MimeType() {
    }

    public MimeType(String type) {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getSchema()
    {
        return schema;
    }

    public void setSchema(String schema)
    {
        this.schema = schema;
    }

    public String getExample()
    {
        return example;
    }

    public void setExample(String example)
    {
        this.example = example;
    }

    public Map<String, List<FormParameter>> getFormParameters()
    {
        return formParameters;
    }

    public void setFormParameters(Map<String, List<FormParameter>> formParameters)
    {
        this.formParameters = formParameters;
    }

    @Override
    public String toString()
    {
        return "MimeType{" +
                "type='" + type + '\'' +
                '}';
    }
}

