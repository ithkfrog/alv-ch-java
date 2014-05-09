package ch.alv.components.data.search;

import ch.alv.components.data.internal.DefaultSearchRepository;
import ch.alv.components.data.mock.MockModelItem;
import ch.alv.components.data.mock.MockSearchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for the {@link ch.alv.components.data.internal.DefaultSearchRepository} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/base-search-repository-test-context.xml")
public class DefaultSearchRepositoryTest {

    @Resource
    private DefaultSearchRepository<MockModelItem, String> repository = new MockSearchRepository();

    @Test
    public void testFindWithValuesProvider() {
        assertNotNull(repository.find(null));
    }

    @Test
    public void testFindWithValuesProviderAndPageable() {
        assertNotNull(repository.find(null, new PageRequest(0, 100)));
    }

    @Test
    public void testFindWithValuesProviderAndCustomSearch() {
        assertNotNull(repository.find(null, "baseSearchRepositorySearch"));
        assertNotNull(repository.find(null, "unknownSearch"));
    }

    @Test
    public void testFindWithValuesProviderAndPageableAndCustomSearch() {
        assertNotNull(repository.find(null, new PageRequest(0, 100), "baseSearchRepositorySearch"));
        assertNotNull(repository.find(null, new PageRequest(0, 100), "unknownSearch"));
    }

    @Test
    public void testFindInvalidSearches() {
        assertNotNull(repository.find(null, new PageRequest(0, 100), "baseSearchRepositoryNonStringSearch"));
        assertNotNull(repository.find(null, new PageRequest(0, 100), "baseSearchRepositoryNullSearch"));
    }

    @Test
    public void testGetAll() {
        Page page = repository.getAll();
        assertEquals(19, page.getContent().size());
    }

    @Test
         public void testGetAllWithIds() {
        List<String> ids = new ArrayList<>();
        ids.add("a");
        ids.add("b");
        ids.add("z");
        Page<MockModelItem> page = repository.getAll(ids);
        assertEquals(2, page.getContent().size());
    }

    @Test
    public void testGetAllWithPageable() {
        Page page = repository.getAll(new PageRequest(0, 10));
        assertEquals(10, page.getContent().size());
    }

}
