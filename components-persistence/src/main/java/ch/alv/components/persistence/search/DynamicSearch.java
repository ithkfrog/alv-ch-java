package ch.alv.components.persistence.search;

import ch.alv.components.persistence.search.internal.*;

import java.util.List;
import java.util.Map;

/**
 * TODO: put some comment
 *
 * @since 1.0.0
 */
public interface DynamicSearch extends Search  {

    DynamicSearch select(String... projections);

    DynamicSearch from(String token, Class<?> entityClass);

    DynamicSearch where(String entityToken, String attributeName);

    DynamicSearch where(String entityToken, String attributeName, Object immutableValue);

    DynamicSearch where(String entityToken, String attributeName, ComparatorType comparatorType);

    DynamicSearch where(String entityToken, String attributeName, ComparatorType comparatorType, Object immutableValue);

    DynamicSearch and(String entityToken, String attributeName);

    DynamicSearch and(String entityToken, String attributeName, Object immutableValue);

    DynamicSearch and(String entityToken, String attributeName, ComparatorType comparatorType);

    DynamicSearch and(String entityToken, String attributeName, ComparatorType comparatorType, Object immutableValue);

    DynamicSearch and(List<Predicate> predicates);

    DynamicSearch and(PredicateGroup group);

    DynamicSearch or(String entityToken, String attributeName);

    DynamicSearch or(String entityToken, String attributeName, Object immutableValue);

    DynamicSearch or(String entityToken, String attributeName, ComparatorType comparatorType);

    DynamicSearch or(String entityToken, String attributeName, ComparatorType comparatorType, Object immutableValue);

    DynamicSearch or(List<Predicate> predicates);

    DynamicSearch or(PredicateGroup group);

    DynamicSearch orderBy(Sorting sorting);

    DynamicSearch orderBy(List<Sorting> sortings);

    String getName();

    void setName(String name);

    String[] getSelects();

    void setSelects(String[] selects);

    List<Entity> getFroms();

    void setFroms(List<Entity> froms);

    List<Predicate> getWheres();

    void setWheres(List<Predicate> wheres);

    List<Sorting> getSortings();

    void setSortings(List<Sorting> sortings);

    String getBaseQuery();

    void setBaseQuery(String baseQuery);

}
