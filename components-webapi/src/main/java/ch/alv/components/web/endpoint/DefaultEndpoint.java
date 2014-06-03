package ch.alv.components.web.endpoint;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.dto.Dto;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the {@link ch.alv.components.web.endpoint.Endpoint} interface.
 *
 * @since 1.0.0
 */
public class DefaultEndpoint implements Endpoint {

    private String moduleName;

    private String storeName;

    private Class<? extends Dto> dtoClass;

    private Class<? extends ModelItem> entityClass;

    private boolean allowGETMethod = true;
    private boolean allowPOSTMethod = true;
    private boolean allowPUTMethod = true;
    private boolean allowDELETEMethod = true;

    private String rolesGET = "";
    private String rolesPOST = "";
    private String rolesPUT = "";
    private String rolesDELETE = "";

    private String defaultSearchName = "";


    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String getModuleName() {
        return moduleName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String getStoreName() {
        return storeName;
    }

    public void setDtoClass(Class<? extends Dto> dtoClass) {
        this.dtoClass = dtoClass;
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return dtoClass;
    }

    public void setEntityClass(Class<? extends ModelItem> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return entityClass;
    }

    @Override
    public List<HttpMethod> getAllowedMethods() {
        List<HttpMethod> allowedMethods = new ArrayList<>();
        if (allowGETMethod) {
            allowedMethods.add(HttpMethod.GET);
        }
        if (allowPOSTMethod) {
            allowedMethods.add(HttpMethod.POST);
        }
        if (allowPUTMethod) {
            allowedMethods.add(HttpMethod.PUT);
        }
        if (allowDELETEMethod) {
            allowedMethods.add(HttpMethod.DELETE);
        }
        return allowedMethods;
    }

    public void setRolesGET(String rolesGET) {
        this.rolesGET = rolesGET;
    }

    @Override
    public String getRolesGET() {
        return rolesGET;
    }

    public void setRolesPOST(String rolesPOST) {
        this.rolesPOST = rolesPOST;
    }

    @Override
    public String getRolesPOST() {
        return rolesPOST;
    }

    public void setRolesPUT(String rolesPUT) {
        this.rolesPUT = rolesPUT;
    }

    @Override
    public String getRolesPUT() {
        return rolesPUT;
    }

    public void setRolesDELETE(String rolesDELETE) {
        this.rolesDELETE = rolesDELETE;
    }

    @Override
    public String getRolesDELETE() {
        return rolesDELETE;
    }

    public void setDefaultSearchName(String defaultSearchName) {
        this.defaultSearchName = defaultSearchName;
    }

    @Override
    public String getDefaultSearchName() {
        return defaultSearchName;
    }

    public void setAllowGETMethod(boolean allowGETMethod) {
        this.allowGETMethod = allowGETMethod;
    }

    public void setAllowPOSTMethod(boolean allowPOSTMethod) {
        this.allowPOSTMethod = allowPOSTMethod;
    }

    public void setAllowPUTMethod(boolean allowPUTMethod) {
        this.allowPUTMethod = allowPUTMethod;
    }

    public void setAllowDELETEMethod(boolean allowDELETEMethod) {
        this.allowDELETEMethod = allowDELETEMethod;
    }
}
