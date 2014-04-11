package ch.alv.components.data.search;

import ch.alv.components.core.search.SearchImpl;
import ch.alv.components.core.search.ValuesProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This component provides methods to execute searches within a certain data source.
 *
 * @since 1.0.0
 */
public interface SearchRepository<TYPE> {

    /**
     * Find entities with a default search configuration. The default search itself has to be configured
     * within the corresponding {@link ch.alv.components.core.search.SearchRenderer}.
     *
     * @param valuesProvider provides the parameters which should be used.
     * @return a page of matching entities.
     */
    public Page<TYPE> findWithDefaultSearch(ValuesProvider valuesProvider);

    /**
     * Find entities with a default search configuration and adapt the result list to be conform to the given pageable.
     * The default search itself has to be configured within the corresponding {@link ch.alv.components.core.search.SearchRenderer}.
     *
     * @param valuesProvider provides the parameters which should be used.
     * @return a page of matching entities.
     */
    public Page<TYPE> findWithDefaultSearch(Pageable pageable, ValuesProvider valuesProvider);

    /**
     * Find entities with the given search configuration.
     *
     * @param valuesProvider provides the parameters which should be used
     * @return a page of matching entities
     */
    public Page<TYPE> findWithCustomSearch(SearchImpl search, ValuesProvider valuesProvider);

    /**
     * Find entities with the given search configuration and adapt the result list to be conform to the given pageable.
     *
     * @param pageable       paging information to be considered when executing the search.
     * @param search         the search configuration which should be used.
     * @param valuesProvider provides the parameters which should be used.
     * @return a page of matching entities
     */
    public Page<TYPE> findWithCustomSearch(Pageable pageable, SearchImpl search, ValuesProvider valuesProvider);

}