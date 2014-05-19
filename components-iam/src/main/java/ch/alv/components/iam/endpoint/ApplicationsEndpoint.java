package ch.alv.components.iam.endpoint;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.iam.endpoint.dto.ApplicationDto;
import ch.alv.components.iam.model.Application;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.internal.BaseEndpoint;
import org.springframework.stereotype.Component;

/**
 * Endpoint for application entities of the iam module.
 *
 * @since 1.0.0
 */
@Component
public class ApplicationsEndpoint extends BaseEndpoint {

    @Override
    public String getModuleName() {
        return "iam";
    }

    @Override
    public String getStoreName() {
        return "applications";
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return ApplicationDto.class;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return Application.class;
    }

}
