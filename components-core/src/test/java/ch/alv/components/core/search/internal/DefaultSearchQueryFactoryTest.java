package ch.alv.components.core.search.internal;

import ch.alv.components.core.search.NoSuchSearchException;
import ch.alv.components.core.search.SearchQueryFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

/**
 * TestCases for the {@link ch.alv.components.core.search.internal.MapBasedValuesProvider}.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-search-query-factory-test-context.xml")
public class DefaultSearchQueryFactoryTest {

    @Resource
    private SearchQueryFactory factory;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testSuccess() {
        assertEquals("testQuery for DefaultSearchQueryFactoryTest",
                factory.createQuery("testSearch", new MapBasedValuesProvider(), DefaultSearchQueryFactoryTest.class));
    }

    @Test
    public void testNoSuchSearchFail() {
        exception.expect(NoSuchSearchException.class);
        exception.expectMessage(containsString("Could not find a search with name 'inexistentSearchName'."));
        factory.createQuery("inexistentSearchName", new MapBasedValuesProvider(), DefaultSearchQueryFactoryTest.class);
    }

    @Test
    public void testAmbiguousFail() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage(containsString("The name 'testSearchClones' is used for more than 1 search."));
        factory.createQuery("testSearchClones", new MapBasedValuesProvider(), DefaultSearchQueryFactoryTest.class);
    }

    @Test
    public void testEmptyName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(containsString("Argument 'searchName' must not be empty."));
        factory.createQuery("", new MapBasedValuesProvider(), DefaultSearchQueryFactoryTest.class);
    }

    @Test
    public void testNullValuesProvider() {
        assertEquals("testQuery for DefaultSearchQueryFactoryTest",
                factory.createQuery("testSearch", null, DefaultSearchQueryFactoryTest.class));
    }

    @Test
    public void testNullTargetClass() {
        exception.expect(NullPointerException.class);
        factory.createQuery("testSearch", new MapBasedValuesProvider(), null);
    }

}
