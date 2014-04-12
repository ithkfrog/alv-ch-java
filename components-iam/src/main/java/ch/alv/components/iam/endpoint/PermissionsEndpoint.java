package ch.alv.components.iam.endpoint;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.iam.endpoint.dto.PermissionDto;
import ch.alv.components.iam.model.Permission;
import ch.alv.components.iam.search.PermissionSearchValuesProvider;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.BaseEndpoint;
import org.springframework.stereotype.Component;

/**
 * Endpoint for permission entities of the iam module.
 *
 * @since 1.0.0
 */
@Component
public class PermissionsEndpoint extends BaseEndpoint {

    @Override
    public String getModuleName() {
        return "iam";
    }

    @Override
    public String getStoreName() {
        return "permissions";
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return PermissionDto.class;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return Permission.class;
    }

    @Override
    public Class<? extends ValuesProvider> getValuesProviderClass() {
        return PermissionSearchValuesProvider.class;
    }

}
