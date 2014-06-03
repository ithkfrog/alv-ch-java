package ch.alv.components.web.endpoint;

import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link DefaultEndpoint} class.
 *
 * @since 1.0.0
 */
public class DefaultEndpointTest {

    @Test
    public void testDefaultValues() {
        DefaultEndpoint endpoint = new DefaultEndpoint();
        assertNull(endpoint.getModuleName());
        assertNull(endpoint.getStoreName());
        assertNull(endpoint.getDtoClass());
        assertNull(endpoint.getEntityClass());

        List<HttpMethod> allowedMethods = endpoint.getAllowedMethods();
        assertEquals(4, allowedMethods.size());
        assertTrue(allowedMethods.contains(HttpMethod.GET));
        assertTrue(allowedMethods.contains(HttpMethod.POST));
        assertTrue(allowedMethods.contains(HttpMethod.PUT));
        assertTrue(allowedMethods.contains(HttpMethod.DELETE));

        assertEquals("", endpoint.getRolesGET());
        assertEquals("", endpoint.getRolesPOST());
        assertEquals("", endpoint.getRolesPUT());
        assertEquals("", endpoint.getRolesDELETE());
        assertEquals("", endpoint.getDefaultSearchName());
    }

    @Test
    public void testSetters() {
        DefaultEndpoint endpoint = new DefaultEndpoint();
        endpoint.setModuleName("testModule");
        assertEquals("testModule", endpoint.getModuleName());
        endpoint.setStoreName("testStore");
        assertEquals("testStore", endpoint.getStoreName());
        endpoint.setDtoClass(TestDto.class);
        assertEquals(TestDto.class, endpoint.getDtoClass());
        endpoint.setEntityClass(TestBean.class);
        assertEquals(TestBean.class, endpoint.getEntityClass());


        endpoint.setAllowGETMethod(false);
        endpoint.setAllowPOSTMethod(false);
        endpoint.setAllowPUTMethod(false);
        endpoint.setAllowDELETEMethod(false);
        List<HttpMethod> allowedMethods = endpoint.getAllowedMethods();
        assertEquals(0, allowedMethods.size());

        String role = "testRole";
        endpoint.setRolesGET("testRole");
        endpoint.setRolesPOST("testRole");
        endpoint.setRolesPUT("testRole");
        endpoint.setRolesDELETE("testRole");
        assertEquals(role, endpoint.getRolesGET());
        assertEquals(role, endpoint.getRolesPOST());
        assertEquals(role, endpoint.getRolesPUT());
        assertEquals(role, endpoint.getRolesDELETE());

        endpoint.setDefaultSearchName("testDefaultSearch");
        assertEquals("testDefaultSearch", endpoint.getDefaultSearchName());
    }


}
