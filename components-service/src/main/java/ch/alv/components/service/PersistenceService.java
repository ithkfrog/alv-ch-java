package ch.alv.components.service;

import ch.alv.components.core.beans.ModelItem;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Persistence Service interface for {@link ModelItem} entities
 *
 * @since 1.0.0
 */
public interface PersistenceService<TYPE extends ModelItem, IDTYPE extends Serializable> extends DataService<TYPE, IDTYPE> {

    /**
     * Write an entity to the datastore. if no id exists, the entity will be treated as
     * a new one. Same, if no item with such an id can be found.
     * @param item the entity to persist.
     * @return an updated version of the entity.
     */
    TYPE save(TYPE item);

    /**
     * Persist a collection of entities.
     * @param items the collection of entities to persist.
     * @return a list of updated entities.
     */
    Page<TYPE> saveAll(List<TYPE> items);

    /**
     * Remove an entity from the datastore. No exception should be thrown,
     * if no entity with such an id exists.
     * @param id the id of the entity that should be removed.
     */
    void delete(IDTYPE id);

}
