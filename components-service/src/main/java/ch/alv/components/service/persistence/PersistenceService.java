package ch.alv.components.service.persistence;

import ch.alv.components.core.model.ModelItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Persistence Service interface for {@link ModelItem} entities
 *
 * @since 1.0.0
 */
public interface PersistenceService<TYPE extends ModelItem, IDTYPE extends Serializable> {

    Page<TYPE> getAll(Pageable pageable);

    TYPE getById(IDTYPE id);

    TYPE save(TYPE item);

    Iterable<TYPE> saveAll(List<TYPE> items);

    void delete(IDTYPE id);

}
