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
public interface PagingDataService<TYPE extends Identifiable<ID>, ID extends Serializable> {

    TYPE find(ID id, Class<TYPE> entityClass) throws ServiceLayerException;

    Page<TYPE> find(Pageable pageable, Collection<ID> id, Class<TYPE> entityClass) throws ServiceLayerException;

    Page<TYPE> find(Pageable pageable, Class<TYPE> entityClass) throws ServiceLayerException;

    Page<TYPE> find(Pageable pageable, String queryName, ValuesProvider params, Class<TYPE> entityClass) throws ServiceLayerException;

    TYPE save(TYPE entity, Class<TYPE> entityClass) throws ServiceLayerException;

    List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws ServiceLayerException;

    void delete(ID id, Class<TYPE> entityClass) throws ServiceLayerException;

    void delete(Collection<ID> ids, Class<TYPE> entityClass) throws ServiceLayerException;

}
