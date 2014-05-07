package ch.alv.components.web.controller;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.core.search.SearchValuesProvider;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.EndpointHelper;
import ch.alv.components.web.endpoint.internal.BaseEndpoint;
import ch.alv.components.web.mapper.MockFactoryTestEntity;
import ch.alv.components.web.mock.TestDto;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Endpoint for controller testing purposes.
 *
 * @since 1.0.0
 */
public class ControllerTestEndpointWithParamConstructorSearch extends BaseEndpoint {

    @Override
    public String getModuleName() {
        return "searchTestModuleWithParamConstructorSearch";
    }

    @Override
    public String getStoreName() {
        return "searchTestStore";
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
        return EndpointHelper.createAllMethodsList();
    }

    @Override
    public String getDefaultSearchName() {
        return "testDefaultSearch";
    }

    @Override
    public Class<? extends SearchValuesProvider> getValuesProviderClass() {
        return ParamConstructorValuesProvider.class;
    }
}
