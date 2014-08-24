package ch.alv.components.data.adapter;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.query.NoSuchQueryProviderException;
import ch.alv.components.data.query.QueryFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * ElasticSearch implementation of the {@link DataStoreAdapter} interface.
 *
 * @since 1.0.0
 */
public class ElasticsearchDataStoreAdapter<TYPE extends Identifiable<String>> implements DataStoreAdapter<TYPE, String> {

    private final ElasticsearchTemplate elasticsearchTemplate;

    private final QueryFactory queryFactory;

    private final Map<String, Object> factoryServices = new HashMap<>();

    public ElasticsearchDataStoreAdapter(ElasticsearchTemplate elasticsearchTemplate, QueryFactory queryFactory) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.queryFactory = queryFactory;
    }

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException {
        if (!elasticsearchTemplate.indexExists(entityClass)) {
            elasticsearchTemplate.createIndex(entityClass);
            elasticsearchTemplate.putMapping(entityClass);
        }
        IndexQuery indexQuery = new IndexQuery();
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        indexQuery.setId(entity.getId());
        indexQuery.setObject(entity);
        elasticsearchTemplate.index(indexQuery);
        elasticsearchTemplate.refresh(entityClass, true);
        return entity;
    }

    @Override
    public TYPE find(String id, Class<TYPE> entityClass) throws DataLayerException {
        GetQuery query = new GetQuery();
        query.setId(id);
        return elasticsearchTemplate.queryForObject(query, entityClass);
    }

    @Override
    public List<TYPE> find(String queryName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException {
        try {
            SearchQuery query = queryFactory.createQuery(queryName, params, factoryServices, entityClass);
            return elasticsearchTemplate.queryForList(query, entityClass);
        } catch (NoSuchQueryProviderException e) {
            throw new DataLayerException("Could not execute query with name '" + queryName + "'.", e);
        }
    }

    @Override
    public List<TYPE> find(Class<TYPE> entityClass) throws DataLayerException {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
        return elasticsearchTemplate.queryForList(searchQuery, entityClass);
    }

    @Override
    public void delete(String id, Class<TYPE> entityClass) throws DataLayerException {
        elasticsearchTemplate.delete(entityClass, id);
    }

}
