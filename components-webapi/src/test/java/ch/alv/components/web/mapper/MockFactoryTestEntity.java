package ch.alv.components.web.mapper;

import ch.alv.components.core.beans.ModelItem;

/**
 * Dto for mapper factory tests.
 *
 * @since 1.0.0
 */
public class MockFactoryTestEntity implements ModelItem<String, Integer> {

    private String id;

    private Integer version;

    public MockFactoryTestEntity() {
    }

    public MockFactoryTestEntity(String id, Integer version) {
        this.id = id;
        this.version = version;
    }

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
