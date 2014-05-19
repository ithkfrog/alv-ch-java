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
public interface DataStoreAdapter<ID extends Serializable> {

    <T extends Identifiable<ID>> T save(T entity, Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable<ID>> T find(ID id, Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable<ID>> List<T> find(String queryName, ValuesProvider params, Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable<ID>> List<T> find(Class<T> entityClass) throws DataLayerException;

    void delete(ID id, Class<?> entityClass) throws DataLayerException;

}
