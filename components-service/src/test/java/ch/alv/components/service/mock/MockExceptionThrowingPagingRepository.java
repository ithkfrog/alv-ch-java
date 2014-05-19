package ch.alv.components.service.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.repository.PagingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * Exception throwing mock implementation of the {@link ch.alv.components.data.repository.PagingRepository} interfce.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockExceptionThrowingPagingRepository implements PagingRepository<String> {

    public static final String MSG = "Test data layer exception.";

    @Override
    public <T extends Identifiable> T save(T entity, Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public <T extends Identifiable> List<T> save(Collection<T> entities, Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public <T extends Identifiable> T find(String id, Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, String searchName, ValuesProvider params, Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public <T extends Identifiable> Page<T> find(Pageable pageable, Collection<String> strings, Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public <T extends Identifiable> void delete(String id, Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public <T extends Identifiable> void delete(Collection<String> ids, Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }
}
