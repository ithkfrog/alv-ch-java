package ch.alv.components.core.beans;

/**
 * Mark versionable entities with this interface.
 *
 * @since 1.0.0
 */
public interface Versionable<VERSION> {

    VERSION getVersion();

    void setVersion(VERSION id);

}
