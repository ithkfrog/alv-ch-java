package ch.alv.components.web.endpoint.filter;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.DefaultEndpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import ch.alv.components.web.mock.MockDto;
import ch.alv.components.web.mock.MockEntity;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Endpoint for endpoint filter testing purposes.
 *
 * @since 1.0.0
 */
public class FilterTestEndpoint extends DefaultEndpoint {

    @Override
    public String getModuleName() {
        return "filterTestModule";
    }

    @Override
    public String getStoreName() {
        return "filterTestStore";
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return MockDto.class;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return MockEntity.class;
    }

    @Override
    public List<HttpMethod> getAllowedMethods() {
        return EndpointHelper.createMethodList(HttpMethod.GET);
    }

}
