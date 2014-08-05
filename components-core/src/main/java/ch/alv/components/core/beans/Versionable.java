package ch.alv.components.core.beans;

import java.io.Serializable;

/**
 * Mark versionable entities with this interface.
 *
 * @since 1.0.0
 */
public interface Versionable<VERSION extends Serializable> {

    VERSION getVersion();

    void setVersion(VERSION id);

}
