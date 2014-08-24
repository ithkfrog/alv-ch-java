package ch.alv.components.data.adapter;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.query.NoSuchQueryProviderException;
import ch.alv.components.data.query.QueryFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * MongoDb implementation of the {@link ch.alv.components.data.adapter.DataStoreAdapter} interface.
 *
 * @since 1.0.0
 */
public class MongoDbDataStoreAdapter<TYPE extends Identifiable<String>> implements DataStoreAdapter<TYPE, String> {

    private final MongoOperations ops;

    private final QueryFactory queryFactory;

    private final Map<String, Object> factoryServices = new HashMap<>();

    public MongoDbDataStoreAdapter(MongoOperations ops, QueryFactory queryFactory) {
        this.ops = ops;
        this.queryFactory = queryFactory;
    }

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        ops.save(entity);
        return entity;
    }

    @Override
    public TYPE find(String id, Class<TYPE> entityClass) throws DataLayerException {
        Query.query(Criteria.where("id").is(id));
        return ops.findOne(Query.query(Criteria.where("id").is(id)), entityClass);
    }

    @Override
    public List<TYPE> find(String queryName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException {
        try {
            Query query = queryFactory.createQuery(queryName, params, factoryServices, entityClass);
            return ops.find(query, entityClass);
        } catch (NoSuchQueryProviderException e) {
            throw new DataLayerException("Could not execute query with name '" + queryName + "'.", e);
        }
    }

    @Override
    public List<TYPE> find(Class<TYPE> entityClass) throws DataLayerException {
        return ops.findAll(entityClass);
    }

    @Override
    public void delete(String id, Class<TYPE> entityClass) throws DataLayerException {
        ops.remove(Query.query(Criteria.where("id").is(id)), entityClass);
    }

}
