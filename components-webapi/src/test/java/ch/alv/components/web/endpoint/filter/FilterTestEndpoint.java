package ch.alv.components.web.endpoint.filter;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.EndpointHelper;
import ch.alv.components.web.endpoint.internal.BaseEndpoint;
import ch.alv.components.web.mapper.MockFactoryTestEntity;
import ch.alv.components.web.mock.TestDto;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Endpoint for endpoint filter testing purposes.
 *
 * @since 1.0.0
 */
public class FilterTestEndpoint extends BaseEndpoint {

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
        return TestDto.class;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return MockFactoryTestEntity.class;
    }

    @Override
    public List<HttpMethod> getAllowedMethods() {
        return EndpointHelper.createMethodList(HttpMethod.GET);
    }

}
