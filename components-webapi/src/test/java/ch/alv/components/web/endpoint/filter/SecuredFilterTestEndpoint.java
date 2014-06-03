package ch.alv.components.web.endpoint.filter;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.DefaultEndpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Endpoint for endpoint filter testing purposes.
 *
 * @since 1.0.0
 */
public class SecuredFilterTestEndpoint extends DefaultEndpoint {

    @Override
    public String getModuleName() {
        return "securedFilterTestModule";
    }

    @Override
    public String getStoreName() {
        return "securedFilterTestStore";
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return null;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return null;
    }

    @Override
    public List<HttpMethod> getAllowedMethods() {
        return EndpointHelper.createAllMethodsList();
    }

    @Override
    public String getRolesGET() {
        return "ROLE_NO_ACCESS";
    }

    @Override
    public String getRolesPOST() {
        return "ROLE_NO_ACCESS";
    }

    @Override
    public String getRolesPUT() {
        return "ROLE_NO_ACCESS";
    }

    @Override
    public String getRolesDELETE() {
        return "ROLE_NO_ACCESS";
    }

}
