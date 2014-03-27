package ch.alv.components.persistence.query;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.persistence.query.internal.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstracted model of a select query.
 *
 * @since 1.0.0
 */
public class SelectQuery {

    private String name;

    private String[] selects = {};

    private List<QueryEntity> froms = new ArrayList<>();

    private List<QueryPredicateItem> wheres = new ArrayList<>();

    private String baseQuery;

    private Map<String, Class<?>> classTokenMap = new HashMap<>();

    private Map<String, Object> values = new HashMap<>();

    public SelectQuery select(String... projections) {
        if (projections == null || projections.length == 0) {
            throw new IllegalStateException("Parameter 'projections' must not be empty.");
        }
        selects = projections;
        return this;
    }

    public SelectQuery from(String token, Class<?> entityClass) {
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
        froms.add(new QueryEntity(entityClass, token));
        return this;
    }

    public SelectQuery where(String entityToken, String attributeName) {
        wheres.add(new QueryPredicate(attributeName, entityToken, QueryPredicateCombination.AND));
        return this;
    }

    public SelectQuery where(String entityToken, String attributeName, Object immutableValue) {
        wheres.add(new QueryPredicate(attributeName, entityToken, QueryPredicateCombination.AND, immutableValue));
        return this;
    }

    public SelectQuery where(String entityToken, String attributeName, QueryOperator operator) {
        wheres.add(new QueryPredicate(attributeName, entityToken, operator, QueryPredicateCombination.AND));
        return this;
    }

    public SelectQuery where(String entityToken, String attributeName, QueryOperator operator, Object immutableValue) {
        wheres.add(new QueryPredicate(attributeName, entityToken, operator, QueryPredicateCombination.AND, immutableValue));
        return this;
    }

    public SelectQuery and(String entityToken, String attributeName) {
        wheres.add(new QueryPredicate(attributeName, entityToken, QueryPredicateCombination.AND));
        return this;
    }

    public SelectQuery and(String entityToken, String attributeName, Object immutableValue) {
        wheres.add(new QueryPredicate(attributeName, entityToken, QueryPredicateCombination.AND, immutableValue));
        return this;
    }

    public SelectQuery and(String entityToken, String attributeName, QueryOperator operator) {
        wheres.add(new QueryPredicate(attributeName, entityToken, operator, QueryPredicateCombination.AND));
        return this;
    }

    public SelectQuery and(String entityToken, String attributeName, QueryOperator operator, Object immutableValue) {
        wheres.add(new QueryPredicate(attributeName, entityToken, operator, QueryPredicateCombination.AND, immutableValue));
        return this;
    }

    public SelectQuery and(List<QueryPredicateItem> predicates) {
        new QueryPredicateGroup(QueryPredicateCombination.AND, predicates);
        return this;
    }

    public SelectQuery or(String entityToken, String attributeName) {
        wheres.add(new QueryPredicate(attributeName, entityToken, QueryPredicateCombination.OR));
        return this;
    }

    public SelectQuery or(String entityToken, String attributeName, Object immutableValue) {
        wheres.add(new QueryPredicate(attributeName, entityToken, QueryPredicateCombination.OR, immutableValue));
        return this;
    }

    public SelectQuery or(String entityToken, String attributeName, QueryOperator operator) {
        wheres.add(new QueryPredicate(attributeName, entityToken, operator, QueryPredicateCombination.OR));
        return this;
    }

    public SelectQuery or(String entityToken, String attributeName, QueryOperator operator, Object immutableValue) {
        wheres.add(new QueryPredicate(attributeName, entityToken, operator, QueryPredicateCombination.OR, immutableValue));
        return this;
    }

    public SelectQuery or(List<QueryPredicateItem> predicates) {
        new QueryPredicateGroup(QueryPredicateCombination.OR, predicates);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSelects() {
        return selects;
    }

    public void setSelects(String[] selects) {
        this.selects = selects;
    }

    public List<QueryEntity> getFroms() {
        return froms;
    }

    public void setFroms(List<QueryEntity> froms) {
        this.froms = froms;
    }

    public List<QueryPredicateItem> getWheres() {
        return wheres;
    }

    public void setWheres(List<QueryPredicateItem> wheres) {
        this.wheres = wheres;
    }

    public String getBaseQuery() {
        return baseQuery;
    }

    public void setBaseQuery(String baseQuery) {
        this.baseQuery = baseQuery;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }
}
