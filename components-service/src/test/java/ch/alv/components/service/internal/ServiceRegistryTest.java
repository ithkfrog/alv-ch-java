package ch.alv.components.service.internal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;


/**
 * Test cases for the {@link ch.alv.components.service.internal.ServiceRegistry} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/service-registry-test-context.xml")
public class ServiceRegistryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private ServiceRegistry registry;

    @Test
    public void testSearchService() {
        assertNotNull(registry.getSearchService("testSearchService"));
    }

    @Test
    public void testSearchServiceFail() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Param 'serviceName' must not be empty.");
        assertNotNull(registry.getSearchService(""));
    }

    @Test
    public void testPersistenceService() {
        assertNotNull(registry.getPersistenceService("testPersistenceService"));
    }

    @Test
    public void testPersistenceServiceFail() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Param 'serviceName' must not be empty.");
        assertNotNull(registry.getPersistenceService(""));
    }

}
