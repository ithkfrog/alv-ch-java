package ch.alv.components.iam.endpoint;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.iam.endpoint.dto.RoleDto;
import ch.alv.components.iam.model.Role;
import ch.alv.components.iam.search.RoleSearchValuesProvider;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.BaseWebApiEndpoint;
import org.springframework.stereotype.Component;

/**
 * Endpoint for role entities of the iam module.
 *
 * @since 1.0.0
 */
@Component
public class RolesEndpoint extends BaseWebApiEndpoint {

    @Override
    public String getModuleName() {
        return "iam";
    }

    @Override
    public String getStoreName() {
        return "roles";
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return RoleDto.class;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return Role.class;
    }

    @Override
    public Class<? extends ValuesProvider> getValuesProviderClass() {
        return RoleSearchValuesProvider.class;
    }

}
