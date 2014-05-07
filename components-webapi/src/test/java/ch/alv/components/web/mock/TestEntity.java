package ch.alv.components.web.mock;

import ch.alv.components.core.beans.ModelItem;

/**
 * Entity for testing purposes.
 *
 * @since 1.0.0
 */
public class TestEntity implements ModelItem<String, Integer> {

    private String id;

    private Integer version;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }
}
