package ch.alv.components.service.data;

import ch.alv.components.core.search.MapBasedValuesProvider;
import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.service.mock.MockExceptionThrowingPagingRepository;
import ch.alv.components.service.mock.MockExceptionThrowingRepository;
import ch.alv.components.service.mock.MockModelItem;
import ch.alv.components.service.mock.MockPagingRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Test cases for the {@link ch.alv.components.service.ServiceRegistry} class.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class DefaultPagingDataServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private PagingDataService<String> service = new DefaultPagingDataService<>(new MockPagingRepository());

    private PagingDataService<String> exceptionService = new DefaultPagingDataService<>(new MockExceptionThrowingPagingRepository());

    private Pageable pageable = new PageRequest(0, 25);

    @Test
    public void testFindAll() throws ServiceLayerException {
        Page entities = service.find(pageable, MockModelItem.class);
        assertEquals(25, entities.getNumberOfElements());

        exception.expect(ServiceLayerException.class);
        exception.expectMessage(MockExceptionThrowingRepository.MSG);
        exceptionService.find(pageable, MockModelItem.class);
    }

    @Test
    public void testFindById() throws ServiceLayerException {
        MockModelItem item = service.find(pageable, MockModelItem.class).getContent().get(0);
        MockModelItem item2 = service.find(pageable, MockModelItem.class).getContent().get(1);
        Object idEntity = service.find((String) service.find(pageable, MockModelItem.class).getContent().get(0).getId(), MockModelItem.class);
        assertNotNull(idEntity);

        List idList = new ArrayList();
        idList.add(item.getId());
        idList.add(item2.getId());

        Page<MockModelItem> items = service.find(pageable, idList, MockModelItem.class);
        assertEquals(idList.size(), items.getNumberOfElements());

        try {
            exception.expect(ServiceLayerException.class);
            exception.expectMessage(MockExceptionThrowingRepository.MSG);
            exceptionService.find("testId", MockModelItem.class);
        } catch (Exception e) {
            // do nothing
        }
        exception.expect(ServiceLayerException.class);
        exception.expectMessage(MockExceptionThrowingRepository.MSG);
        exceptionService.find(pageable, idList, MockModelItem.class);
    }

    @Test
    public void testFindWithCustomQuery() throws ServiceLayerException {
        Page entities = service.find(pageable, "testQuery", new MapBasedValuesProvider(), MockModelItem.class);
        assertEquals(0, entities.getNumberOfElements());

        exception.expect(ServiceLayerException.class);
        exception.expectMessage(MockExceptionThrowingRepository.MSG);
        exceptionService.find(pageable, "testQuery", new MapBasedValuesProvider(), MockModelItem.class);
    }

    @Test
    public void testSaveOne() throws ServiceLayerException {
        MockModelItem item = new MockModelItem("testItem");
        MockModelItem persistedItem = service.save(item, MockModelItem.class);
        assertEquals("testItem", persistedItem.getName());

        exception.expect(ServiceLayerException.class);
        exception.expectMessage(MockExceptionThrowingRepository.MSG);
        exceptionService.save(item, MockModelItem.class);
    }

    @Test
    public void testSaveMulti() throws ServiceLayerException {
        MockModelItem item = new MockModelItem("testItem");
        MockModelItem item2 = new MockModelItem("testItem2");
        List<MockModelItem> items = new ArrayList<>();
        items.add(item);
        items.add(item2);

        List<MockModelItem> persistedItems = service.save(items, MockModelItem.class);
        assertEquals("testItem", persistedItems.get(0).getName());
        assertEquals("testItem2", persistedItems.get(1).getName());

        exception.expect(ServiceLayerException.class);
        exception.expectMessage(MockExceptionThrowingRepository.MSG);
        exceptionService.save(items, MockModelItem.class);
    }

    @Test
    public void testDeleteOne() throws ServiceLayerException {
        MockModelItem item = new MockModelItem("testItem");
        service.delete((String) item.getId(), MockModelItem.class);
        exception.expect(ServiceLayerException.class);
        exception.expectMessage(MockExceptionThrowingRepository.MSG);
        exceptionService.delete((String) item.getId(), MockModelItem.class);
    }

    @Test
    public void testDeleteMulti() throws ServiceLayerException {
        List<String> ids = new ArrayList<>();
        ids.add("a");
        ids.add("b");
        service.delete(ids, MockModelItem.class);

        exception.expect(ServiceLayerException.class);
        exception.expectMessage(MockExceptionThrowingRepository.MSG);
        exceptionService.delete(ids, MockModelItem.class);
    }

}
