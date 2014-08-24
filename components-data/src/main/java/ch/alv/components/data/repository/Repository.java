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
public interface Repository<TYPE extends Identifiable<ID>, ID extends Serializable> {

    TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException;

    List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws DataLayerException;

    TYPE find(ID id, Class<TYPE> entityClass) throws DataLayerException;

    List<TYPE> find(String searchName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException;

    List<TYPE> find(Class<TYPE> entityClass) throws DataLayerException;

    List<TYPE> find(Collection<ID> ids, Class<TYPE> entityClass) throws DataLayerException;

    void delete(ID id, Class<TYPE> entityClass) throws DataLayerException;

    void delete(Collection<ID> ids, Class<TYPE> entityClass) throws DataLayerException;

}
