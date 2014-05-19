package ch.alv.components.iam.endpoint;

import ch.alv.components.iam.endpoint.dto.RoleDto;
import ch.alv.components.iam.model.Role;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link RolesEndpoint} class
 *
 * @since 1.0.0
 */
public class RolesEndpointTest {


    @Test
    public void testConfiguration() {
        Endpoint endpoint = new RolesEndpoint();
        assertEquals("iam", endpoint.getModuleName());
        assertEquals("roles", endpoint.getStoreName());
        assertEquals("", endpoint.getDefaultSearchName());
        assertEquals(EndpointHelper.createAllMethodsList(), endpoint.getAllowedMethods());
        assertEquals(RoleDto.class, endpoint.getDtoClass());
        assertEquals(Role.class, endpoint.getEntityClass());
        assertEquals("", endpoint.getRolesGET());
        assertEquals("", endpoint.getRolesPOST());
        assertEquals("", endpoint.getRolesPUT());
        assertEquals("", endpoint.getRolesDELETE());
    }

}
