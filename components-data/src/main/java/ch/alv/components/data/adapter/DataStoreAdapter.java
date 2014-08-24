package ch.alv.components.data.adapter;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;

import java.io.Serializable;
import java.util.List;

/**
 * Datastore adapters realize the connection between repositories and data stores.
 *
 * @since 1.0.0
 */
public interface DataStoreAdapter<TYPE extends Identifiable<ID>, ID extends Serializable> {

    TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException;

    TYPE find(ID id, Class<TYPE> entityClass) throws DataLayerException;

    List<TYPE> find(String queryName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException;

    List<TYPE> find(Class<TYPE> entityClass) throws DataLayerException;

    void delete(ID id, Class<TYPE> entityClass) throws DataLayerException;

}
