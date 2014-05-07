package ch.alv.components.service.internal;

import ch.alv.components.service.mock.MockModelItem;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Test cases for the {@link ch.alv.components.service.internal.ServiceRegistry} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-persistence-service-test-context.xml")
@SuppressWarnings("unchecked")
public class DefaultPersistenceServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private DefaultPersistenceService service;

    @Test
    public void testFindAll() {
        Page<?> page = service.findAll();
        assertEquals(100, page.getSize());
        assertEquals(0, page.getNumber());
        assertEquals(19, page.getTotalElements());
        assertEquals(1, page.getTotalPages());
    }

    @Test
    public void testFindWithPageable() {
        Pageable pageable = new PageRequest(1, 5);
        Page<?> page = service.findAll(pageable);
        assertEquals(5, page.getSize());
        assertEquals(1, page.getNumber());
        assertEquals(19, page.getTotalElements());
        assertEquals(4, page.getTotalPages());
    }

    @Test
    public void testFindOne() {
        MockModelItem<String, Integer> item = (MockModelItem<String, Integer>) service.findOne("a");
        assertEquals("a", item.getId());
    }

    @Test
    public void testSave() {
        int elementsBefore = service.findAll().getNumberOfElements();
        MockModelItem<String, Integer> newItem = new MockModelItem<>();
        MockModelItem<String, Integer> item = (MockModelItem<String, Integer>) service.save(newItem);
        assertNotNull(item.getId());
        assertEquals(elementsBefore + 1, service.findAll().getNumberOfElements());
    }

    @Test
    public void testSaveWithId() {
        int elementsBefore = service.findAll().getNumberOfElements();
        MockModelItem<String, Integer> newItem = new MockModelItem<>();
        newItem.setId("y");
        MockModelItem<String, Integer> item = (MockModelItem<String, Integer>) service.save(newItem);
        assertEquals("y", item.getId());
        assertEquals(elementsBefore + 1, service.findAll().getNumberOfElements());
    }

    @Test
    public void testSaveAll() {
        int elementsBefore = service.findAll().getNumberOfElements();
        MockModelItem<String, Integer> newItem = new MockModelItem<>();
        MockModelItem<String, Integer> newItem2 = new MockModelItem<>();
        List<MockModelItem<String, Integer>> list = new ArrayList<>();
        list.add(newItem);
        list.add(newItem2);
        Page<?> page = service.saveAll(list);
        assertEquals(100, page.getSize());
        assertEquals(0, page.getNumber());
        assertEquals(2, page.getTotalElements());
        assertEquals(1, page.getTotalPages());
        assertEquals(elementsBefore + 2, service.findAll().getNumberOfElements());
    }

    @Test
    public void testDelete() {
        MockModelItem<String, Integer> newItem = new MockModelItem<>();
        newItem.setId("z");
        service.save(newItem);
        int elementsBefore = service.findAll().getNumberOfElements();
        service.delete("z");
        assertEquals(elementsBefore - 1, service.findAll().getNumberOfElements());
    }

}
