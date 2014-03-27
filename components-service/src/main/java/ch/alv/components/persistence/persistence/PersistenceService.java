package ch.alv.components.persistence.persistence;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.persistence.repository.ParamValuesProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Persistence Service interface for {@link ModelItem} entities
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public interface PersistenceService<TYPE extends ModelItem, IDTYPE extends Serializable> {

    Page<TYPE> findAll(Pageable pageable);

    Page<TYPE> find(ParamValuesProvider valuesProvider);

    Page<TYPE> find(String searchName, ParamValuesProvider valuesProvider);

    Page<TYPE> find(Pageable pageable, ParamValuesProvider valuesProvider);

    Page<TYPE> find(Pageable pageable, String searchName, ParamValuesProvider valuesProvider);

    TYPE getById(IDTYPE id);

    TYPE save(TYPE item);

    Iterable<TYPE> save(List<TYPE> items);

    void delete(IDTYPE id);

}
