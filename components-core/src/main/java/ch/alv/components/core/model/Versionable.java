package ch.alv.components.core.model;

/**
 * Mark versionable entities with this interface.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public interface Versionable<VERSION> {

    VERSION getVersion();

    void setVersion(VERSION id);

}
