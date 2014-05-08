package ch.alv.components.web.endpoint;

import ch.alv.components.web.search.internal.DefaultMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.web.endpoint.internal.BaseEndpoint} class.
 *
 * @since 1.0.0
 */
public class BaseEndpointTest {

    @Test
    public void testConfiguration() {
        Endpoint endpoint = new TestEndpoint();
        assertEquals("testModule", endpoint.getModuleName());
        assertEquals("testStore", endpoint.getStoreName());
        assertEquals("", endpoint.getDefaultSearchName());
        assertEquals("testModule.testStor.service", endpoint.getServiceName());
        assertEquals(EndpointHelper.createAllMethodsList(), endpoint.getAllowedMethods());
        assertEquals(TestDto.class, endpoint.getDtoClass());
        assertEquals(TestBean.class, endpoint.getEntityClass());
        assertEquals(DefaultMapper.class, endpoint.getValuesProviderClass());
        assertEquals("", endpoint.getRolesGET());
        assertEquals("", endpoint.getRolesPOST());
        assertEquals("", endpoint.getRolesPUT());
        assertEquals("", endpoint.getRolesDELETE());
    }

}
