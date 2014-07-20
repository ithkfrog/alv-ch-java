package ch.alv.components.web.api.mock;

import ch.alv.components.core.beans.Identifiable;

/**
 * Simple class as mocked resource.
 *
 * @since 1.0.0
 */
public class MockResource implements Identifiable<String> {

    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

}
