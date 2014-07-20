package ch.alv.components.web.api.config;

/**
 * Describes a param of a query.
 *
 * @since 1.0.0
 */
public class QueryParameter extends BaseParameter {

    private static final long serialVersionUID = 1546319756684036185L;

    public QueryParameter() {
    }

    public QueryParameter(String name, ParameterType type, boolean required) {
        super(name, type, required);
    }
}
