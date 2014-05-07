package ch.alv.components.web.endpoint;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.internal.BaseEndpoint;

/**
 * Endpoint for testing purposes.
 *
 * @since 1.0.0
 */
public class TestEndpoint extends BaseEndpoint {

    @Override
    public String getModuleName() {
        return "testModule";
    }

    @Override
    public String getStoreName() {
        return "testStore";
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return TestDto.class;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return TestBean.class;
    }
}
