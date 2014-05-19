package ch.alv.components.service.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.repository.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Exception throwing mock implementation of the {@link ch.alv.components.data.repository.Repository} interfce.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class MockExceptionThrowingRepository implements Repository<String> {

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
    public <T extends Identifiable> List<T> find(String searchName, ValuesProvider params, Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public <T extends Identifiable> List<T> find(Class<T> entityClass) throws DataLayerException {
        throw new DataLayerException(MSG);
    }

    @Override
    public <T extends Identifiable> List<T> find(Collection<String> ids, Class<T> entityClass) throws DataLayerException {
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
