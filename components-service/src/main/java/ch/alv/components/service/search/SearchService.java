package ch.alv.components.service.search;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.search.ValuesProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Persistence Service interface for {@link ch.alv.components.core.model.ModelItem} entities
 *
 * @since 1.0.0
 */
public interface SearchService<TYPE extends ModelItem> {

    TYPE getById(String id);

    Page<TYPE> getAll(Pageable pageable);

    Page<TYPE> find(ValuesProvider valuesProvider);

    Page<TYPE> find(String searchName, ValuesProvider valuesProvider);

    Page<TYPE> find(Pageable pageable, ValuesProvider valuesProvider);

    Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider valuesProvider);

}
