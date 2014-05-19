package ch.alv.components.web.endpoint.internal;

import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Base implementation of the {@link ch.alv.components.web.endpoint.Endpoint} interface.
 *
 * @since 1.0.0
 */
public abstract class BaseEndpoint implements Endpoint {

    @Override
    public List<HttpMethod> getAllowedMethods() {
        return EndpointHelper.createAllMethodsList();
    }

    @Override
    public String getRolesGET() {
        return "";
    }

    @Override
    public String getRolesPOST() {
        return "";
    }

    @Override
    public String getRolesPUT() {
        return "";
    }

    @Override
    public String getRolesDELETE() {
        return "";
    }

    @Override
    public String getDefaultSearchName() {
        return "";
    }

}
