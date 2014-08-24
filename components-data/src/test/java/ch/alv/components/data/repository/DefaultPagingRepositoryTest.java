package ch.alv.components.data.repository;

import ch.alv.components.core.search.MapBasedValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.mock.MockDataStoreAdapter;
import ch.alv.components.data.mock.MockExceptionThrowingDataStoreAdapter;
import ch.alv.components.data.mock.MockModelItem;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for the {@link DefaultPagingRepository} class. Tests implicitly the {@link DefaultRepository}.
 *
 * @since 1.0.0
 */
public class DefaultPagingRepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final DefaultPagingRepository<MockModelItem,String> repository = new DefaultPagingRepository<>(new MockDataStoreAdapter());
    private final DefaultPagingRepository<MockModelItem,String> exceptionRepository = new DefaultPagingRepository<>(new MockExceptionThrowingDataStoreAdapter());

    private final Pageable pageable = new PageRequest(0, 5);

    @Test
    public void testFindAll() throws DataLayerException {
        Page result = repository.find(pageable, MockModelItem.class);
        assertEquals(5, result.getContent().size());

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MockExceptionThrowingDataStoreAdapter.MSG);
        exceptionRepository.find(pageable, MockModelItem.class);
    }

    @Test
    public void testFindById() throws DataLayerException {
        MockModelItem result = repository.find("a", MockModelItem.class);
        assertEquals("a", result.getId());

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MockExceptionThrowingDataStoreAdapter.MSG);
        exceptionRepository.find("a", MockModelItem.class);
    }

    @Test
    public void testFindByIds() throws DataLayerException {
        List<String> ids = new ArrayList<>();
        ids.add("a");
        ids.add("b");
        Page<MockModelItem> result = repository.find(pageable, ids, MockModelItem.class);
        assertEquals(2, result.getNumberOfElements());

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MockExceptionThrowingDataStoreAdapter.MSG);
        exceptionRepository.find(pageable, ids, MockModelItem.class);
    }

    @Test
    public void testFindWithQuery() throws DataLayerException {
        Page<MockModelItem> result = repository.find(pageable, "testQuery", new MapBasedValuesProvider(), MockModelItem.class);
        assertEquals(5, result.getNumberOfElements());

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MockExceptionThrowingDataStoreAdapter.MSG);
        exceptionRepository.find(pageable, "testQuery", new MapBasedValuesProvider(), MockModelItem.class);
    }

    @Test
    public void testSave() throws DataLayerException {
        MockModelItem item = new MockModelItem("testName");
        MockModelItem persistedItem = repository.save(item, MockModelItem.class);
        assertEquals("testName", persistedItem.getName());

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MockExceptionThrowingDataStoreAdapter.MSG);
        exceptionRepository.save(item, MockModelItem.class);
    }

    @Test
    public void testMultipleSave() throws DataLayerException {
        List<MockModelItem> items = new ArrayList<>();
        items.add(new MockModelItem("testName"));
        items.add(new MockModelItem("testName 2"));
        List<MockModelItem> persistedItems = repository.save(items, MockModelItem.class);
        assertEquals(2, persistedItems.size());
        assertEquals("testName", persistedItems.get(0).getName());
        assertEquals("testName 2", persistedItems.get(1).getName());

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MockExceptionThrowingDataStoreAdapter.MSG);
        exceptionRepository.save(items, MockModelItem.class);
    }

    @Test
    public void testDelete() throws DataLayerException {
        repository.delete("c", MockModelItem.class);
        assertNull(repository.find("c", MockModelItem.class));

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MockExceptionThrowingDataStoreAdapter.MSG);
        exceptionRepository.delete("c", MockModelItem.class);
    }

    @Test
    public void testMultipleDelete() throws DataLayerException {
        List<String> ids = new ArrayList<>();
        ids.add("d");
        ids.add("e");
        repository.delete(ids, MockModelItem.class);
        assertNull(repository.find("d", MockModelItem.class));
        assertNull(repository.find("e", MockModelItem.class));

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage(MockExceptionThrowingDataStoreAdapter.MSG);
        exceptionRepository.delete(ids, MockModelItem.class);
    }
}
