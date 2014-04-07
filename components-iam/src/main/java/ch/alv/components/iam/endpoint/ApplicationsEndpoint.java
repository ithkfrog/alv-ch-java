package ch.alv.components.iam.endpoint;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.iam.endpoint.dto.ApplicationDto;
import ch.alv.components.iam.model.Application;
import ch.alv.components.iam.search.ApplicationSearchValuesProvider;
import ch.alv.components.persistence.search.ValuesProvider;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.BaseWebApiEndpoint;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Endpoint for application entities of the iam module.
 *
 * @since 1.0.0
 */
@Component
public class ApplicationsEndpoint extends BaseWebApiEndpoint {

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

    @Override
    public Class<? extends ValuesProvider> getValuesProviderClass() {
        return ApplicationSearchValuesProvider.class;
    }

}
