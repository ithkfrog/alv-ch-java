package ch.alv.components.web.api.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.web.api.TargetEntity;

/**
 * Simple class as mocked resource.
 *
 * @since 1.0.0
 */
@TargetEntity(entityClass = MockResource.class)
public class MockTargetedResource implements Identifiable<String> {

    private String id;

    @Override
    public String getId() {
        return id;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
