package ch.alv.components.data.search;

import ch.alv.components.data.internal.BaseSearchRepository;
import ch.alv.components.data.mock.TestBaseSearchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

/**
 * Tests for the {@link ch.alv.components.data.internal.BaseSearchRepository} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/base-search-repository-test-context.xml")
public class BaseSearchRepositoryTest {

    @Resource
    private BaseSearchRepository repository = new TestBaseSearchRepository();

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

}
