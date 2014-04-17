package ch.alv.components.iam.endpoint;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.search.SearchValuesProvider;
import ch.alv.components.iam.endpoint.dto.RoleDto;
import ch.alv.components.iam.model.Role;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.BaseEndpoint;
import ch.alv.components.web.search.StringWebValuesProvider;
import org.springframework.stereotype.Component;

/**
 * Endpoint for role entities of the iam module.
 *
 * @since 1.0.0
 */
@Component
public class RolesEndpoint extends BaseEndpoint {

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
    public Class<? extends SearchValuesProvider> getValuesProviderClass() {
        return StringWebValuesProvider.class;
    }

}
