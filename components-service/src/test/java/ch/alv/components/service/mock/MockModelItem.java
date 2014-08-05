package ch.alv.components.service.mock;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.data.model.BaseModelItem;

import java.io.Serializable;

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
        setVersion(version);
        setName(name);
    }

    public MockModelItem(String id, Integer version, String name) {
        setId(id);
        setVersion(version);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

