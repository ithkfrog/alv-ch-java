package ch.alv.components.web.endpoint;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.dto.Dto;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Interface for WebApi endpoints
 *
 * @since 1.0.0
 */
public interface Endpoint {

    String getModuleName();

    String getStoreName();

    Class<? extends Dto> getDtoClass();

    Class<? extends ModelItem> getEntityClass();

    List<HttpMethod> getAllowedMethods();

    String getRolesGET();

    String getRolesPOST();

    String getRolesPUT();

    String getRolesDELETE();

    String getDefaultSearchName();

}
