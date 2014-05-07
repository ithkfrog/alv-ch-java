package ch.alv.components.service;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.core.search.SearchValuesProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Persistence Service interface for {@link ch.alv.components.core.beans.ModelItem} entities
 *
 * @since 1.0.0
 */
public interface SearchService<TYPE extends ModelItem, ID extends Serializable> extends DataService<TYPE, ID> {

    Page<TYPE> find(SearchValuesProvider searchValuesProvider);

    Page<TYPE> find(String searchName, SearchValuesProvider searchValuesProvider);

    Page<TYPE> find(Pageable pageable, SearchValuesProvider searchValuesProvider);

    Page<TYPE> find(Pageable pageable, String searchName, SearchValuesProvider searchValuesProvider);

}
