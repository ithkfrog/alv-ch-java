package ch.alv.components.core.search;

import java.util.List;

/**
 * Abstract API to configure search logic.
 *
 * @since 1.0.0
 */
public interface Search {

    Class<?> getTargetClass();

    List<Projection> getProjections();

    List<SearchSource> getSources();

    List<Predicate> getPredicates();

    List<PredicateBoost> getBoosts();

    List<SearchSorting> getSortings();

    String getName();

}
