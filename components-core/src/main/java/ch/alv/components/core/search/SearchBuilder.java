package ch.alv.components.core.search;

import ch.alv.components.core.utils.StringHelper;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Builder for abstract searches.
 *
 * @since 1.0.0
 */
public class SearchBuilder {

    private SearchImpl search = new SearchImpl();

    public SearchBuilder find(String sourceToken, Class<?> targetClass) {
        if (targetClass == null) {
            throw new IllegalStateException("Parameter 'targetClass' must not be null.");
        }
        search.getProjections().add(new Projection(sourceToken, targetClass.getSimpleName()));
        return this;
    }

    public SearchBuilder find(String sourceToken, String attributeName) {
        if (sourceToken == null) {
            throw new IllegalStateException("Parameter 'sourceToken' must not be null or empty.");
        }
        if (attributeName == null) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        search.getProjections().add(new Projection(sourceToken, attributeName));
        return this;
    }

    public SearchBuilder in(String sourceToken, String sourceName) {
        if (StringHelper.isEmpty(sourceToken)) {
            throw new IllegalStateException("Parameter 'sourceToken' must not be null or empty.");
        }
        if (StringHelper.isEmpty(sourceName)) {
            throw new IllegalStateException("Parameter 'sourceName' must not be null or empty.");
        }
        search.getSources().add(new SearchSource(sourceToken, sourceName));
        return this;
    }

    public SearchBuilder where(String sourceToken, String attributeName) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, null));
        return this;
    }

    public SearchBuilder where(String sourceToken, String attributeName, Object value) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, value));
        return this;
    }

    public SearchBuilder where(String sourceToken, String attributeName, ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, null, comparatorType));
        return this;
    }

    public SearchBuilder where(String sourceToken, String attributeName, Object value, ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, value, comparatorType));
        return this;
    }

    public SearchBuilder where(GroupPredicate group) {
        search.getPredicates().add(group);
        return this;
    }

    public SearchBuilder where(List<Predicate> list) {
        and(list);
        return this;
    }

    public SearchBuilder and(String sourceToken, String attributeName) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, null, ConcatType.AND));
        return this;
    }

    public SearchBuilder and(String sourceToken, String attributeName, Object value) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, value, ConcatType.AND));
        return this;
    }

    public SearchBuilder and(String sourceToken, String attributeName, Object value, ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        if (comparatorType == null) {
            throw new IllegalStateException("Parameter 'comparatorType' must not be null.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, value, ConcatType.AND, comparatorType));
        return this;
    }

    public SearchBuilder and(String sourceToken, String attributeName, ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        if (comparatorType == null) {
            throw new IllegalStateException("Parameter 'comparatorType' must not be null.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, null, ConcatType.AND, comparatorType));
        return this;
    }

    public SearchBuilder and(List<Predicate> predicates) {
        if (CollectionUtils.isEmpty(predicates)) {
            throw new IllegalStateException("Parameter 'predicates' must not be null.");
        }
        GroupPredicate group = new GroupPredicate();
        group.setConcatType(ConcatType.AND);
        group.getPredicates().addAll(predicates);
        search.getPredicates().add(group);
        return this;
    }

    public SearchBuilder and(GroupPredicate group) {
        if (group == null) {
            throw new IllegalStateException("Parameter 'group' must not be null.");
        }
        group.setConcatType(ConcatType.AND);
        search.getPredicates().add(group);
        return this;
    }

    public SearchBuilder or(String sourceToken, String attributeName) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, null, ConcatType.OR));
        return this;
    }

    public SearchBuilder or(String sourceToken, String attributeName, Object value) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, value, ConcatType.OR));
        return this;
    }

    public SearchBuilder or(String sourceToken, String attributeName,ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        if (comparatorType == null) {
            throw new IllegalStateException("Parameter 'comparatorType' must not be null.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, null, ConcatType.OR, comparatorType));
        return this;
    }

    public SearchBuilder or(String sourceToken, String attributeName, Object value, ComparatorType comparatorType) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        if (comparatorType == null) {
            throw new IllegalStateException("Parameter 'comparatorType' must not be null.");
        }
        search.getPredicates().add(new SinglePredicate(sourceToken, attributeName, value, ConcatType.OR, comparatorType));
        return this;
    }

    public SearchBuilder or(List<Predicate> predicates) {
        if (CollectionUtils.isEmpty(predicates)) {
            throw new IllegalStateException("Parameter 'predicates' must not be null.");
        }
        GroupPredicate group = new GroupPredicate();
        group.setConcatType(ConcatType.OR);
        group.getPredicates().addAll(predicates);
        search.getPredicates().add(group);
        return this;
    }

    public SearchBuilder or(GroupPredicate group) {
        if (group == null) {
            throw new IllegalStateException("Parameter 'group' must not be null.");
        }
        group.setConcatType(ConcatType.OR);
        search.getPredicates().add(group);
        return this;
    }

    public SearchBuilder sort(String sourceToken, String attributeName, SortType sortType) {
        if (StringHelper.isEmpty(sourceToken)) {
            throw new IllegalStateException("Parameter 'sourceToken' must not be null or empty.");
        }
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null or empty.");
        }
        if (sortType == null) {
            throw new IllegalStateException("Parameter 'sortType' must not be null.");
        }
        search.getSortings().add(new SearchSorting(sourceToken, attributeName, sortType));
        return this;
    }

    public SearchBuilder sortAsc(String sourceToken, String attributeName) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null.");
        }
        search.getSortings().add(new SearchSorting(sourceToken, attributeName, SortType.ASC));
        return this;
    }

    public SearchBuilder sortDesc(String sourceToken, String attributeName) {
        if (StringHelper.isEmpty(attributeName)) {
            throw new IllegalStateException("Parameter 'attributeName' must not be null.");
        }
        search.getSortings().add(new SearchSorting(sourceToken, attributeName, SortType.DESC));
        return this;
    }

    public SearchImpl build() {
        return search;
    }

    public SearchBuilder reset() {
        this.search = new SearchImpl();
        return this;
    }

}
