package ch.alv.components.web.endpoint;

import ch.alv.components.core.beans.ModelItem;

/**
 * Bean for testing purposes.
 *
 * @since 1.0.0
 */
public class TestBean implements ModelItem<String, Integer> {

    @Override
    public String getId() {
        return null;  // not required
    }

    @Override
    public void setId(String s) {
        // not required
    }

    @Override
    public Integer getVersion() {
        return null;  // not required
    }

    @Override
    public void setVersion(Integer id) {
        // not required
    }
}
