package ch.alv.components.web.endpoint.internal;

import ch.alv.components.core.search.SearchValuesProvider;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import ch.alv.components.web.search.internal.DefaultWebSearchValuesProvider;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Base implementation of the {@link ch.alv.components.web.endpoint.Endpoint} interface.
 *
 * @since 1.0.0
 */
public abstract class BaseEndpoint implements Endpoint {

    @Override
    public Class<? extends SearchValuesProvider> getValuesProviderClass() {
        return DefaultWebSearchValuesProvider.class;
    }

    @Override
    public List<HttpMethod> getAllowedMethods() {
        return EndpointHelper.createAllMethodsList();
    }

    @Override
    public String getServiceName() {
        return getModuleName() + "." + getStoreName().substring(0, getStoreName().length() - 1) + ".service";
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
