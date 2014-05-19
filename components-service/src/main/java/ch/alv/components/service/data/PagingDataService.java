package ch.alv.components.service.data;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.service.ServiceLayerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Paging persistence Service interface for {@link ch.alv.components.core.beans.Identifiable} entities
 *
 * @since 1.0.0
 */
public interface PagingDataService<ID extends Serializable> {

    <T extends Identifiable> T find(ID id, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> Page<T> find(Pageable pageable, Collection<ID> id, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> Page<T> find(Pageable pageable, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> Page<T> find(Pageable pageable, String queryName, ValuesProvider params, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> T save(T entity, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable<ID>> void delete(ID id, Class<T> entityClass) throws ServiceLayerException;

    <T extends Identifiable<ID>> void delete(Collection<ID> ids, Class<T> entityClass) throws ServiceLayerException;

}
