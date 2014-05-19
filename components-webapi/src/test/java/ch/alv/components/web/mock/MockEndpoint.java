package ch.alv.components.web.mock;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.internal.BaseEndpoint;
import org.springframework.stereotype.Component;

/**
 * Mocked endpoint for unit tests.
 *
 * @since 1.0.0
 */
@Component
public class MockEndpoint extends BaseEndpoint {
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
        return MockDto.class;
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return MockEntity.class;
    }
}
