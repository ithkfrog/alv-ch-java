package ch.alv.components.web.endpoint;

import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.core.utils.ReflectionUtilsException;
import ch.alv.components.web.mock.MockEntity;
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
 * Test cases for the {@link SpringBeansEndpointProvider} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/endpoint-registry-test.xml")
public class SpringBeansEndpointProviderTest {

    @Resource
    private SpringBeansEndpointProvider registry;

    @Resource
    private ApplicationContextProvider contextProvider;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetByModuleAndStoreName() {
        Endpoint endPoint = registry.getEndpointStore("testModule", "testStore");
        assertNotNull(endPoint);
    }

    @Test
    public void testGetByUnknownStoreName() {
        Endpoint endPoint = registry.getEndpointStore("module", "testStore");
        assertNull(endPoint);
    }

    @Test
    public void testGetByUnknownModuleAndStoreName() {
        Endpoint endPoint = registry.getEndpointStore("testModule", "store");
        assertNull(endPoint);
    }

    @Test
    public void testGetByEmptyModuleAndStoreName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Param 'moduleName' must not be empty.");
        Endpoint endPoint = registry.getEndpointStore("", "testStore");
        assertNotNull(endPoint);
    }

    @Test
    public void testGetByModuleAndEmptyStore() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Param 'storeName' must not be empty.");
        Endpoint endPoint = registry.getEndpointStore("testModule", "");
        assertNotNull(endPoint);
    }

    @Test
    public void testGetUnknown() {
        Endpoint endPoint = registry.getEndpointStore("nope", "nope");
        assertNull(endPoint);
    }

    @Test
    public void testGetByClass() {
        Endpoint endPoint = registry.getEndpointByResource(MockEntity.class);
        assertNotNull(endPoint);
    }

    @Test
    public void testGetUnknownByClass() {
        Endpoint endPoint = registry.getEndpointByResource(TestBean.class);
        assertNull(endPoint);
    }

    @Test
    public void testGetByNullClass() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Param 'entityClass' must not be null.");
        Endpoint endPoint = registry.getEndpointByResource(null);
        assertNotNull(endPoint);
    }

    @Test
    public void fullCoverageForStaticTest() throws ReflectionUtilsException {
        new SpringBeansEndpointProvider(contextProvider);
    }

}
