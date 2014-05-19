package ch.alv.components.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


/**
 * Test cases for the {@link ch.alv.components.service.ServiceRegistry} class.
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
    public void testGetDataService() {
        assertNotNull(registry.getDataService("testDataServiceOne"));
    }

    @Test
    public void testGetDataServiceWithUnknownService() {
        exception.expect(NoSuchBeanDefinitionException.class);
        exception.expectMessage("No bean named 'testDataServiceUnknown' is defined");
        assertNull(registry.getDataService("testDataServiceUnknown"));
    }

    @Test
    public void testGetDataServiceWithEmptyServiceName() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Param 'serviceName' must not be empty.");
        assertNotNull(registry.getDataService(""));
    }

    @Test
    public void testGetDataServiceWithNullServiceName() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Param 'serviceName' must not be empty.");
        assertNotNull(registry.getDataService(null));
    }

}
