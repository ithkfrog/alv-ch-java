package ch.alv.components.web.mock;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.internal.BaseEndpoint;
import org.springframework.stereotype.Component;

/**
 * Endpoint for testing purposes.
 *
 * @since 1.0.0
 */
@Component
public class SearchTestEndpoint extends BaseEndpoint {

    @Override
    public String getModuleName() {
        return "searchTestModule";
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
        return TestEntity.class;
    }

    @Override
    public String getServiceName() {
        return "searchService";
    }

}
