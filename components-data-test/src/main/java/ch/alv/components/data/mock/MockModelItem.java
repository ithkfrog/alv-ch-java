package ch.alv.components.data.mock;

import ch.alv.components.data.model.BaseModelItem;

/**
 * Mock class for ModelItems
 *
 * @since 1.0.0
 */
public class MockModelItem extends BaseModelItem {

    private String name;

    public MockModelItem() {
    }

    public MockModelItem(String name) {
        this.name = name;
    }

    public MockModelItem(Integer version, String name) {
        this.name = name;
        setVersion(version);
    }

    public MockModelItem(String id, Integer version, String name) {
        this.name = name;
        setId(id);
        setVersion(version);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
