package ch.alv.components.service.data;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.service.ServiceLayerException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Persistence Service interface for {@link ch.alv.components.core.beans.Identifiable} entities
 *
 * @since 1.0.0
 */
public interface DataService<TYPE extends Identifiable<ID>, ID extends Serializable> {

    TYPE find(ID id, Class<TYPE> entityClass) throws ServiceLayerException;

    List<TYPE> find(Collection<ID> id, Class<TYPE> entityClass) throws ServiceLayerException;

    List<TYPE> find(Class<TYPE> entityClass) throws ServiceLayerException;

    List<TYPE> find(String queryName, ValuesProvider params, Class<TYPE> entityClass) throws ServiceLayerException;

    TYPE save(TYPE entity, Class<TYPE> entityClass) throws ServiceLayerException;

    List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws ServiceLayerException;

    void delete(ID id, Class<TYPE> entityClass) throws ServiceLayerException;

    void delete(Collection<ID> ids, Class<TYPE> entityClass) throws ServiceLayerException;

}
