package ch.alv.components.persistence.search;

/**
 * Immutable non-dynamic searches should be realized with this interface.
 *
 * @since 1.0.0
 */
public interface HardCodedSearch extends Search {

    String getTemplate();

}
