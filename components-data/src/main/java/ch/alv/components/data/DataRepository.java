package ch.alv.components.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Low-level interface for repositories.
 *
 * @since 1.0.0
 */
public interface DataRepository<TYPE, ID>  {

    /**
     * Returns all entities.
     *
     * @return all entities.
     */
    Page<TYPE> getAll();

    /**
     * Returns a {@link org.springframework.data.domain.Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable the Pageable to consider
     * @return a page of entities
     */
    Page<TYPE> getAll(Pageable pageable);

    Page<TYPE> getAll(Iterable<ID> ids);

    /**
     * Get an object with the given id.
     *
     * @param id the
     * @return a page of matching entities.
     */
    TYPE getOne(ID id);
}
