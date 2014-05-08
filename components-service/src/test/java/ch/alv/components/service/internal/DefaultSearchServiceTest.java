package ch.alv.components.service.internal;

import ch.alv.components.core.search.internal.MapBasedValuesProvider;
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

import static org.junit.Assert.assertEquals;


/**
 * Test cases for the {@link ServiceRegistry} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-search-service-test-context.xml")
public class DefaultSearchServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private DefaultSearchService service;

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
    @SuppressWarnings("unchecked")
    public void testFindOne() {
        MockModelItem<String, Integer> item = (MockModelItem<String, Integer>) service.findOne("a");
        assertEquals("a", item.getId());
    }

    @Test
    public void testFindWithValuesProvider() {
        Page<?> page = service.find(new MapBasedValuesProvider());
        assertEquals(100, page.getSize());
        assertEquals(0, page.getNumber());
        assertEquals(19, page.getTotalElements());
        assertEquals(1, page.getTotalPages());
    }

    @Test
    public void testFindWithPageableAndValuesProvider() {
        Pageable pageable = new PageRequest(1, 5);
        Page<?> page = service.find(pageable, new MapBasedValuesProvider());
        assertEquals(5, page.getSize());
        assertEquals(1, page.getNumber());
        assertEquals(19, page.getTotalElements());
        assertEquals(4, page.getTotalPages());
    }

    @Test
    public void testFindWithSearchAndValuesProvider() {
        Page<?> page = service.find("defaultSearchServiceSearch", new MapBasedValuesProvider());
        assertEquals(100, page.getSize());
        assertEquals(0, page.getNumber());
        assertEquals(19, page.getTotalElements());
        assertEquals(1, page.getTotalPages());
    }

    @Test
    public void testFindWithSearchAndPageableAndValuesProvider() {
        Pageable pageable = new PageRequest(1, 5);
        Page<?> page = service.find(pageable, "defaultSearchServiceSearch", new MapBasedValuesProvider());
        assertEquals(5, page.getSize());
        assertEquals(1, page.getNumber());
        assertEquals(19, page.getTotalElements());
        assertEquals(4, page.getTotalPages());
    }

}
