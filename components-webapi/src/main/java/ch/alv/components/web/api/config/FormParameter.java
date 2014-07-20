package ch.alv.components.web.api.config;

/**
 * Parameter of a form description.
 *
 * @since 1.0.0
 */
public class FormParameter extends BaseParameter {

    private static final long serialVersionUID = -3136563635062704105L;

    public FormParameter() {
    }

    public FormParameter(String name, ParameterType type, boolean required) {
        super(name, type, required);
    }
}
