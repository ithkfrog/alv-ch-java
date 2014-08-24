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
public class MockExceptionThrowingDataStoreAdapter<TYPE extends Identifiable<String>> implements DataStoreAdapter<TYPE, String> {

    public static final String MSG = "testMessage";

    @Override
    public TYPE save(TYPE entity, Class<TYPE> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }

    @Override
    public TYPE find(String id, Class<TYPE> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }

    @Override
    public List<TYPE> find(String queryName, ValuesProvider params, Class<TYPE> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }

    @Override
    public List<TYPE> find(Class<TYPE> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }

    @Override
    public void delete(String id, Class<TYPE> entityClass) throws DataLayerException {
        throw new IllegalStateException(MSG);
    }
}
