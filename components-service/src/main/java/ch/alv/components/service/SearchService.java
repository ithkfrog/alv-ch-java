package ch.alv.components.service;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.core.search.ValuesProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Persistence Service interface for {@link ch.alv.components.core.beans.ModelItem} entities
 *
 * @since 1.0.0
 */
public interface SearchService<TYPE extends ModelItem, ID extends Serializable> extends DataService<TYPE, ID> {

    Page<TYPE> find(ValuesProvider valuesProvider);

    Page<TYPE> find(String searchName, ValuesProvider valuesProvider);

    Page<TYPE> find(Pageable pageable, ValuesProvider valuesProvider);

    Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider valuesProvider);

}
