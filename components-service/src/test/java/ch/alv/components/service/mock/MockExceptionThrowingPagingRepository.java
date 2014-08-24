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
public class MockExceptionThrowingPagingRepository<TYPE extends Identifiable<String>> implements PagingRepository<TYPE, String> {

    public static final String MSG = "Test data layer exception.";

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public List<TYPE> save(Collection<TYPE> entities, Class<TYPE> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public TYPE find(String id, Class<TYPE> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, String searchName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, Class<TYPE> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public Page<TYPE> find(Pageable pageable, Collection<String> strings, Class<TYPE> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public void delete(String id, Class<TYPE> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public void delete(Collection<String> ids, Class<TYPE> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }
}
