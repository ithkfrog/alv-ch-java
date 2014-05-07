package ch.alv.components.data.mock;

import ch.alv.components.core.beans.ModelItem;

import java.io.Serializable;

/**
 * Mock class for ModelItems
 *
 * @since 1.0.0
 */
public class MockModelItem<ID_TYPE extends Serializable, VERSION_TYPE extends Serializable> implements ModelItem<ID_TYPE, VERSION_TYPE> {

    private ID_TYPE id;

    private VERSION_TYPE version;

    private String name;


    public MockModelItem() {
    }

    public MockModelItem(String name) {
        this.name = name;
    }

    public MockModelItem(VERSION_TYPE version, String name) {
        this.version = version;
        this.name = name;
    }

    public MockModelItem(ID_TYPE id, VERSION_TYPE version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    @Override
    public ID_TYPE getId() {
        return id;
    }

    @Override
    public void setId(ID_TYPE id) {
        this.id = id;
    }

    @Override
    public VERSION_TYPE getVersion() {
        return version;
    }

    @Override
    public void setVersion(VERSION_TYPE version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
