package ch.alv.components.data.repository;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Interface for a generic repository
 *
 * @since 1.0.0
 */
public interface Repository<ID extends Serializable> {

    <T extends Identifiable> T save(T entity, Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable> T find(ID id, Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable> List<T> find(String searchName, ValuesProvider params, Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable> List<T> find(Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable> List<T> find(Collection<ID> ids, Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable> void delete(ID id, Class<T> entityClass) throws DataLayerException;

    <T extends Identifiable> void delete(Collection<ID> ids, Class<T> entityClass) throws DataLayerException;

}
