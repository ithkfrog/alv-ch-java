package ch.alv.components.iam.endpoint;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.iam.endpoint.dto.UserDto;
import ch.alv.components.iam.model.User;
import ch.alv.components.iam.search.UserSearchValuesMapper;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.internal.BaseEndpoint;
import ch.alv.components.web.search.RequestParamsToValuesMapper;
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
    public Class<? extends RequestParamsToValuesMapper> getValuesProviderClass() {
        return UserSearchValuesMapper.class;
    }

}
