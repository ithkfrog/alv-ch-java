package ch.alv.components.web.api.config;

/**
 * Holds information about a header parameter of an endpoint.
 *
 * @since 1.0.0
 */
public class HeaderParameter extends BaseParameter {

    private static final long serialVersionUID = -8285959500890330482L;

    public HeaderParameter() {
    }

    public HeaderParameter(String name, ParameterType type, boolean required) {
        super(name, type, required);
    }

}
