package ch.alv.components.web.endpoint;

import ch.alv.components.core.utils.ReflectionUtilsException;
import ch.alv.components.web.mock.TestEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test cases for the {@link EndpointRegistry} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/endpoint-registry-test.xml")
public class EndpointRegistryTest {

    @Resource
    private EndpointRegistry registry;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetByModuleAndStoreName() {
        Endpoint endPoint = registry.getEndpoint("testModule", "testStore");
        assertNotNull(endPoint);
    }

    @Test
    public void testGetByUnknownStoreName() {
        Endpoint endPoint = registry.getEndpoint("module", "testStore");
        assertNull(endPoint);
    }

    @Test
    public void testGetByUnknownModuleAndStoreName() {
        Endpoint endPoint = registry.getEndpoint("testModule", "store");
        assertNull(endPoint);
    }

    @Test
    public void testGetByEmptyModuleAndStoreName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Param 'moduleName' must not be empty.");
        Endpoint endPoint = registry.getEndpoint("", "testStore");
        assertNotNull(endPoint);
    }

    @Test
    public void testGetByModuleAndEmptyStore() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Param 'storeName' must not be empty.");
        Endpoint endPoint = registry.getEndpoint("testModule", "");
        assertNotNull(endPoint);
    }

    @Test
    public void testGetUnknown() {
        Endpoint endPoint = registry.getEndpoint("nope", "nope");
        assertNull(endPoint);
    }

    @Test
    public void testGetByClass() {
        Endpoint endPoint = registry.getEndpoint(TestEntity.class);
        assertNotNull(endPoint);
    }

    @Test
    public void testGetUnknownByClass() {
        Endpoint endPoint = registry.getEndpoint(TestBean.class);
        assertNull(endPoint);
    }

    @Test
    public void testGetByNullClass() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Param 'entityClass' must not be null.");
        Endpoint endPoint = registry.getEndpoint(null);
        assertNotNull(endPoint);
    }

    @Test
    public void fullCoverageForStaticTest() throws ReflectionUtilsException {
        new EndpointRegistry();
    }

}
