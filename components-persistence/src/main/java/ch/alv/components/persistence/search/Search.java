package ch.alv.components.persistence.search;

import java.util.List;

/**
 * Holds a definition of a custom search algorithm.
 *
 * @since 1.0.0
 */
public interface Search {

    String getName();

    List<SearchParam> getParams();

    String getQueryFactoryName();

}
