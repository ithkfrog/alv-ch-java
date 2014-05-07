package ch.alv.components.web.mapper;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.internal.BaseEndpoint;
import org.springframework.stereotype.Component;

/**
 * Endpoint for mapper factory tests.
 *
 * @since 1.0.0
 */
@Component
public class MockFactoryTestEndpoint extends BaseEndpoint {
    @Override
    public String getModuleName() {
        return "mapperFactoryModule";
    }

    @Override
    public String getStoreName() {
        return "mapperFactoryModule";
    }

    @Override
    public Class<? extends Dto> getDtoClass() {
        return null;  // not required
    }

    @Override
    public Class<? extends ModelItem> getEntityClass() {
        return MockFactoryTestEntity.class;
    }
}
