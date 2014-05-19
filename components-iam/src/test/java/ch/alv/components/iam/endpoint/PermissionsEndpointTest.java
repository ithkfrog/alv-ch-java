package ch.alv.components.iam.endpoint;

import ch.alv.components.iam.endpoint.dto.PermissionDto;
import ch.alv.components.iam.model.Permission;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.iam.endpoint.PermissionsEndpoint} class
 *
 * @since 1.0.0
 */
public class PermissionsEndpointTest {


    @Test
    public void testConfiguration() {
        Endpoint endpoint = new PermissionsEndpoint();
        assertEquals("iam", endpoint.getModuleName());
        assertEquals("permissions", endpoint.getStoreName());
        assertEquals("", endpoint.getDefaultSearchName());
        assertEquals(EndpointHelper.createAllMethodsList(), endpoint.getAllowedMethods());
        assertEquals(PermissionDto.class, endpoint.getDtoClass());
        assertEquals(Permission.class, endpoint.getEntityClass());
        assertEquals("", endpoint.getRolesGET());
        assertEquals("", endpoint.getRolesPOST());
        assertEquals("", endpoint.getRolesPUT());
        assertEquals("", endpoint.getRolesDELETE());
    }

}
