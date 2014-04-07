package ch.alv.components.persistence.repository;

import ch.alv.components.persistence.search.ValuesProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Custom repositories should provide custom find methods.
 *
 * @since 1.0.0
 */
public interface CustomRepository<TYPE> {

    Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider valuesProvider);

    Page<TYPE> find(Pageable pageable, ValuesProvider valuesProvider);

    Page<TYPE> find(String searchName, ValuesProvider valuesProvider);

    Page<TYPE> find(ValuesProvider valuesProvider);

}
