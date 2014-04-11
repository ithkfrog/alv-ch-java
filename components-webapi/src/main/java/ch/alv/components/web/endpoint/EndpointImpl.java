package ch.alv.components.web.endpoint;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.web.dto.Dto;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Configure endpoints with objects of this class.
 *
 * @since 1.0.0
 */
public class EndpointImpl implements Endpoint {

    private String moduleName;

    private String storeName;

    private Class<? extends Dto> dtoClass;

    private Class<? extends ModelItem> entityClass;

    private Class<? extends ValuesProvider> valuesProviderClass;

    public Class<? extends ValuesProvider> getValuesProviderClass() {
        return valuesProviderClass;
    }

    public void setValuesProviderClass(Class<? extends ValuesProvider> valuesProviderClass) {
        this.valuesProviderClass = valuesProviderClass;
    }

    private List<HttpMethod> allowedMethods = new ArrayList<>(Arrays.asList(new HttpMethod[]{HttpMethod.GET}));

    private String serviceName;

    private String rolesGET = "";

    private String rolesPOST = "";

    private String rolesPUT = "";

    private String rolesDELETE = "";

    @Override
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return dtoClass;
    }

    public void setDtoClass(Class<? extends Dto> dtoClass) {
        this.dtoClass = dtoClass;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<? extends ModelItem> entityClass) {
        this.entityClass = entityClass;
    }

    public List<HttpMethod> getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(List<HttpMethod> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getRolesGET() {
        return rolesGET;
    }

    public void setRolesGET(String rolesGET) {
        this.rolesGET = rolesGET;
    }

    @Override
    public String getRolesPOST() {
        return rolesPOST;
    }

    public void setRolesPOST(String rolesPOST) {
        this.rolesPOST = rolesPOST;
    }

    @Override
    public String getRolesPUT() {
        return rolesPUT;
    }

    public void setRolesPUT(String rolesPUT) {
        this.rolesPUT = rolesPUT;
    }

    @Override
    public String getRolesDELETE() {
        return rolesDELETE;
    }

    public void setRolesDELETE(String rolesDELETE) {
        this.rolesDELETE = rolesDELETE;
    }

}
