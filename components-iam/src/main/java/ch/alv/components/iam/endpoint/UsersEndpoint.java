package ch.alv.components.iam.endpoint;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.search.SearchValuesProvider;
import ch.alv.components.iam.endpoint.dto.UserDto;
import ch.alv.components.iam.model.User;
import ch.alv.components.iam.search.UserSearchSearchValuesProvider;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.BaseEndpoint;
import org.springframework.stereotype.Component;

/**
 * Endpoint for user entities of the iam module.
 *
 * @since 1.0.0
 */
@Component
public class UsersEndpoint extends BaseEndpoint {

    @Override
    public String getModuleName() {
        return "iam";
    }

    @Override
    public String getStoreName() {
        return "users";
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return UserDto.class;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return User.class;
    }

    @Override
    public Class<? extends SearchValuesProvider> getValuesProviderClass() {
        return UserSearchSearchValuesProvider.class;
    }

}
