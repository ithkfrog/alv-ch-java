package ch.alv.components.core.model;

import java.io.Serializable;

/**
 * Fundamental interface for any model item.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public interface ModelItem<ID extends Serializable, VERSION extends Serializable> extends Identifiable<ID>, Versionable<VERSION> {
}
