package ch.alv.components.web.endpoint;

import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.web.search.DefaultWebValuesProvider;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Base implementation of the {@link Endpoint} interface.
 *
 * @since 1.0.0
 */
public abstract class BaseWebApiEndpoint implements Endpoint {

    @Override
    public Class<? extends ValuesProvider> getValuesProviderClass() {
        return DefaultWebValuesProvider.class;
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

}
