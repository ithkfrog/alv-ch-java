package ch.alv.components.iam.endpoint;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.iam.endpoint.dto.UserDto;
import ch.alv.components.iam.model.User;
import ch.alv.components.iam.search.UserSearchValuesProvider;
import ch.alv.components.persistence.search.ValuesProvider;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.BaseWebApiEndpoint;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Endpoint for user entities of the iam module.
 *
 * @since 1.0.0
 */
@Component
public class UsersEndpoint extends BaseWebApiEndpoint {

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
    public Class<? extends ValuesProvider> getValuesProviderClass() {
        return UserSearchValuesProvider.class;
    }

}
