package ch.alv.components.persistence.search;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.persistence.search.internal.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of the {@link DynamicSearch} interface.
 *
 * @since 1.0.0
 */
public class DynamicSearchImpl implements DynamicSearch {

    private String name;

    private String[] selects = {};

    private List<Entity> froms = new ArrayList<>();

    private List<Predicate> wheres = new ArrayList<>();

    private List<Sorting> sortings = new ArrayList<>();

    private String baseQuery;

    private Map<String, Class<?>> classTokenMap = new HashMap<>();

    @Override
    public DynamicSearch select(String... projections) {
        if (projections == null || projections.length == 0) {
            selects = new String[] { "a.*" };
        } else {
            selects = projections;
        }
        return this;
    }

    @Override
    public DynamicSearch from(String token, Class<?> entityClass) {
        if (entityClass == null) {
            throw new IllegalStateException("Parameter 'entityClass' must not be null.");
        }
        if (StringHelper.isEmpty(token)) {
            throw new IllegalStateException("Parameter 'token' must not be empty.");
        }
        if (classTokenMap.containsKey(token) && !classTokenMap.get(token).equals(token)) {
            throw new IllegalStateException("A mapping with this token already exists for another class.");
        }
        classTokenMap.put(token, entityClass);
        froms.add(new Entity(entityClass, token));
        return this;
    }

    @Override
    public DynamicSearch where(String entityToken, String attributeName) {
        wheres.add(new PredicateItem(entityToken ,attributeName, ConcatType.AND));
        return this;
    }

    @Override
    public DynamicSearch where(String entityToken, String attributeName, Object immutableValue) {
        wheres.add(new PredicateItem(entityToken ,attributeName, ConcatType.AND, immutableValue));
        return this;
    }

    @Override
    public DynamicSearch where(String entityToken, String attributeName, ComparatorType comparatorType) {
        wheres.add(new PredicateItem(entityToken ,attributeName, comparatorType, ConcatType.AND));
        return this;
    }

    @Override
    public DynamicSearch where(String entityToken, String attributeName, ComparatorType comparatorType, Object immutableValue) {
        wheres.add(new PredicateItem(entityToken ,attributeName, comparatorType, ConcatType.AND, immutableValue));
        return this;
    }

    @Override
    public DynamicSearch and(String entityToken, String attributeName) {
        wheres.add(new PredicateItem(entityToken ,attributeName, ConcatType.AND));
        return this;
    }

    @Override
    public DynamicSearch and(String entityToken, String attributeName, Object immutableValue) {
        wheres.add(new PredicateItem(entityToken ,attributeName, ConcatType.AND, immutableValue));
        return this;
    }

    @Override
    public DynamicSearch and(String entityToken, String attributeName, ComparatorType comparatorType) {
        wheres.add(new PredicateItem(entityToken ,attributeName, comparatorType, ConcatType.AND));
        return this;
    }

    @Override
    public DynamicSearch and(String entityToken, String attributeName, ComparatorType comparatorType, Object immutableValue) {
        wheres.add(new PredicateItem(entityToken ,attributeName, comparatorType, ConcatType.AND, immutableValue));
        return this;
    }

    @Override
    public DynamicSearch and(List<Predicate> predicates) {
        wheres.add(new PredicateGroup(ConcatType.AND, predicates));
        return this;
    }

    @Override
    public DynamicSearch and(PredicateGroup group) {
        wheres.add(group);
        return this;
    }

    @Override
    public DynamicSearch or(String entityToken, String attributeName) {
        wheres.add(new PredicateItem(entityToken ,attributeName, ConcatType.OR));
        return this;
    }

    @Override
    public DynamicSearch or(String entityToken, String attributeName, Object immutableValue) {
        wheres.add(new PredicateItem(entityToken ,attributeName, ConcatType.OR, immutableValue));
        return this;
    }

    @Override
    public DynamicSearch or(String entityToken, String attributeName, ComparatorType comparatorType) {
        wheres.add(new PredicateItem(entityToken ,attributeName, comparatorType, ConcatType.OR));
        return this;
    }

    @Override
    public DynamicSearch or(String entityToken, String attributeName, ComparatorType comparatorType, Object immutableValue) {
        wheres.add(new PredicateItem(entityToken ,attributeName, comparatorType, ConcatType.OR, immutableValue));
        return this;
    }

    @Override
    public DynamicSearch or(List<Predicate> predicates) {
        wheres.add(new PredicateGroup(ConcatType.OR, predicates));
        return this;
    }

    @Override
    public DynamicSearch or(PredicateGroup group) {
        wheres.add(group);
        return this;
    }

    @Override
    public DynamicSearch orderBy(Sorting sorting) {
        sortings.add(sorting);
        return this;
    }

    @Override
    public DynamicSearch orderBy(List<Sorting> sortings) {
        this.sortings.addAll(sortings);
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String[] getSelects() {
        return selects;
    }

    @Override
    public void setSelects(String[] selects) {
        this.selects = selects;
    }

    @Override
    public List<Entity> getFroms() {
        return froms;
    }

    @Override
    public void setFroms(List<Entity> froms) {
        this.froms = froms;
    }

    @Override
    public List<Predicate> getWheres() {
        return wheres;
    }

    @Override
    public void setWheres(List<Predicate> wheres) {
        this.wheres = wheres;
    }

    @Override
    public List<Sorting> getSortings() {
        return sortings;
    }

    @Override
    public void setSortings(List<Sorting> sortings) {
        this.sortings = sortings;
    }

    @Override
    public String getBaseQuery() {
        return baseQuery;
    }

    @Override
    public void setBaseQuery(String baseQuery) {
        this.baseQuery = baseQuery;
    }

}
