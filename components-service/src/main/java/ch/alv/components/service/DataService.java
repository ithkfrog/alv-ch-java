package ch.alv.components.service;

import ch.alv.components.core.beans.ModelItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Persistence Service interface for {@link ch.alv.components.core.beans.ModelItem} entities
 *
 * @since 1.0.0
 */
public interface DataService<TYPE extends ModelItem, IDTYPE extends Serializable> {

    /**
     * Search for an entity by id.
     * @param id the id of the requested entity
     * @return the required entity of null, if no such entity exists.
     */
    TYPE findOne(IDTYPE id);

    /**
     * Returns a list of all persisted entities.
     * @return a list with all stored entities.
     */
    Page<TYPE> findAll();

    /**
     * Returns a list of all persisted entities.
     * @param pageable the pageable to consider.
     * @return a list with all stored entities (or a part of them, if the pagesize
     *         is lower than the number of entities.
     */
    Page<TYPE> findAll(Pageable pageable);

}
