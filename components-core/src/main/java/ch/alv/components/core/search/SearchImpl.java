package ch.alv.components.core.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract definition of a search.
 *
 * @since 1.0.0
 */
public class SearchImpl implements Search {

    private String name;

    private Class<?> targetClass;

    private List<Projection> projections = new ArrayList<>();

    private List<SearchSource> sources = new ArrayList<>();

    private List<Predicate> predicates = new ArrayList<>();

    private List<PredicateBoost> boosts = new ArrayList<>();

    private List<SearchSorting> sortings = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public List<Projection> getProjections() {
        return projections;
    }

    public void setProjections(List<Projection> projections) {
        this.projections = projections;
    }

    @Override
    public List<SearchSource> getSources() {
        return sources;
    }

    public void setSources(List<SearchSource> sources) {
        this.sources = sources;
    }

    @Override
    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public List<PredicateBoost> getBoosts() {
        return boosts;
    }

    public void setBoosts(List<PredicateBoost> boosts) {
        this.boosts = boosts;
    }

    @Override
    public List<SearchSorting> getSortings() {
        return sortings;
    }

    public void setSortings(List<SearchSorting> sortings) {
        this.sortings = sortings;
    }
}
