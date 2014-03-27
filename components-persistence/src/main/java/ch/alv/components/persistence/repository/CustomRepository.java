package ch.alv.components.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Custom repositories should provide custom find methods.
 *
 * @since 1.0.0
 */
public interface CustomRepository<TYPE> {

    Page<TYPE> find(Pageable pageable, String searchName, ParamValuesProvider valuesProvider);

    Page<TYPE> find(Pageable pageable, ParamValuesProvider valuesProvider);

    Page<TYPE> find(String searchName, ParamValuesProvider valuesProvider);

    Page<TYPE> find(ParamValuesProvider valuesProvider);

}
