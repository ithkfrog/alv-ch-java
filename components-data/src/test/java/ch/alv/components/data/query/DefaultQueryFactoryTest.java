package ch.alv.components.data.query;

import ch.alv.components.core.search.MapBasedValuesProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link DefaultQueryFactory} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-query-factory-test-context.xml")
public class DefaultQueryFactoryTest {

    @Resource
    private QueryFactory factory;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testCreateQuery() throws NoSuchQueryProviderException {
        assertEquals("testQuery", factory.createQuery("mockQueryProvider", new MapBasedValuesProvider(), new HashMap<String, Object>(), String.class));

        expectedException.expect(NoSuchQueryProviderException.class);
        expectedException.expectMessage("Could not find a QueryProvider with name 'unknown'. Execution aborted.");
        factory.createQuery("unknown", new MapBasedValuesProvider(), new HashMap<String, Object>(), String.class);
    }

}
