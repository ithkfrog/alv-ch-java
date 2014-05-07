package ch.alv.components.iam.endpoint;

import ch.alv.components.iam.endpoint.dto.UserDto;
import ch.alv.components.iam.model.User;
import ch.alv.components.iam.search.UserSearchValuesProvider;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link UsersEndpoint} class
 *
 * @since 1.0.0
 */
public class UsersEndpointTest {


    @Test
    public void testConfiguration() {
        Endpoint endpoint = new UsersEndpoint();
        assertEquals("iam", endpoint.getModuleName());
        assertEquals("users", endpoint.getStoreName());
        assertEquals("", endpoint.getDefaultSearchName());
        assertEquals("iam.user.service", endpoint.getServiceName());
        assertEquals(EndpointHelper.createAllMethodsList(), endpoint.getAllowedMethods());
        assertEquals(UserDto.class, endpoint.getDtoClass());
        assertEquals(User.class, endpoint.getEntityClass());
        assertEquals(UserSearchValuesProvider.class, endpoint.getValuesProviderClass());
        assertEquals("", endpoint.getRolesGET());
        assertEquals("", endpoint.getRolesPOST());
        assertEquals("", endpoint.getRolesPUT());
        assertEquals("", endpoint.getRolesDELETE());
    }

}
