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
public interface DataService<ID extends Serializable> {

    <T extends Identifiable> T find(ID id, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> List<T> find(Collection<ID> id, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> List<T> find(Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> List<T> find(String queryName, ValuesProvider params, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> T save(T entity, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable<ID>> void delete(ID id, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable<ID>> void delete(Collection<ID> ids, Class<T> entityClass) throws ServiceLayerException;

}
