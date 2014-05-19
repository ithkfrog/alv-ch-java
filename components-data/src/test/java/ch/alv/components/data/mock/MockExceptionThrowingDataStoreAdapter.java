package ch.alv.components.data.mock;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.search.ValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.adapter.DataStoreAdapter;

import java.util.List;

/**
 * Mock implementation of the {@link ch.alv.components.data.adapter.DataStoreAdapter} interface.
 *
 * @since 1.0.0
 */
public class MockExceptionThrowingDataStoreAdapter implements DataStoreAdapter<String> {

    public static final String MSG = "testMessage";

    @Override
    public <T extends Identifiable<String>> T save(T entity, Class<T> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }

    @Override
    public <T extends Identifiable<String>> T find(String id, Class<T> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }

    @Override
    public <T extends Identifiable<String>> List<T> find(String queryName, ValuesProvider params, Class<T> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }

    @Override
    public <T extends Identifiable<String>> List<T> find(Class<T> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }

    @Override
    public void delete(String id, Class<?> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }
}
