package ch.alv.components.data.repository;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Interface for a generic repository
 *
 * @since 1.0.0
 */
public interface PagingRepository<TYPE extends Identifiable<ID>, ID extends Serializable> {

    TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException;

    List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws DataLayerException;

    TYPE find(ID id, Class<TYPE> entityClass) throws DataLayerException;

    Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException;

    Page<TYPE> find(Pageable pageable, Class<TYPE> entityClass) throws DataLayerException;

    Page<TYPE> find(Pageable pageable, Collection<ID> ids, Class<TYPE> entityClass) throws DataLayerException;

    void delete(ID id, Class<TYPE> entityClass) throws DataLayerException;

    void delete(Collection<ID> ids, Class<TYPE> entityClass) throws DataLayerException;

}
