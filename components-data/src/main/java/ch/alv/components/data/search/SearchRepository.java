package ch.alv.components.data.search;

import ch.alv.components.core.search.SearchValuesProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This component provides methods to execute searches within a certain data source.
 *
 * @since 1.0.0
 */
public interface SearchRepository<TYPE> {

    /**
     * Get all objects of an entity type.
     *
     * @param pageable paging information to be considered when executing the search.
     * @return a page of matching entities.
     */
    Page<TYPE> getAll(Pageable pageable);

    /**
     * Get all objects of an entity type.
     *
     * @param id the
     * @return a page of matching entities.
     */
    TYPE getById(String id);

    /**
     * Find entities with a default search configuration.
     *
     * @param searchValuesProvider provides the parameters which should be used.
     * @return a page of matching entities.
     */
    Page<TYPE> findWithDefaultSearch(SearchValuesProvider searchValuesProvider);

    /**
     * Find entities with a default search configuration and adapt the result list to be conform to the given pageable.
     *
     * @param pageable       paging information to be considered when executing the search.
     * @param searchValuesProvider provides the parameters which should be used.
     * @return a page of matching entities.
     */
    Page<TYPE> findWithDefaultSearch(Pageable pageable, SearchValuesProvider searchValuesProvider);

    /**
     * Find entities with the given search configuration.
     *
     * @param searchName     the name of the search that should be used.
     * @param searchValuesProvider provides the parameters which should be used
     * @return a page of matching entities
     */
    Page<TYPE> findWithCustomSearch(String searchName, SearchValuesProvider searchValuesProvider);

    /**
     * Find entities with the given search configuration and adapt the result list to be conform to the given pageable.
     *
     * @param pageable       paging information to be considered when executing the search.
     * @param searchName     the name of the search that should be used.
     * @param searchValuesProvider provides the parameters which should be used.
     * @return a page of matching entities
     */
    Page<TYPE> findWithCustomSearch(Pageable pageable, String searchName, SearchValuesProvider searchValuesProvider);

}
