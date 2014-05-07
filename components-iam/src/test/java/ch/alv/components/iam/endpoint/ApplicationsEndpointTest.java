package ch.alv.components.iam.endpoint;

import ch.alv.components.iam.endpoint.dto.ApplicationDto;
import ch.alv.components.iam.model.Application;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointHelper;
import ch.alv.components.web.search.internal.DefaultWebSearchValuesProvider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ApplicationsEndpoint} class
 *
 * @since 1.0.0
 */
public class ApplicationsEndpointTest {


    @Test
    public void testConfiguration() {
        Endpoint endpoint = new ApplicationsEndpoint();
        assertEquals("iam", endpoint.getModuleName());
        assertEquals("applications", endpoint.getStoreName());
        assertEquals("", endpoint.getDefaultSearchName());
        assertEquals("iam.application.service", endpoint.getServiceName());
        assertEquals(EndpointHelper.createAllMethodsList(), endpoint.getAllowedMethods());
        assertEquals(ApplicationDto.class, endpoint.getDtoClass());
        assertEquals(Application.class, endpoint.getEntityClass());
        assertEquals(DefaultWebSearchValuesProvider.class, endpoint.getValuesProviderClass());
        assertEquals("", endpoint.getRolesGET());
        assertEquals("", endpoint.getRolesPOST());
        assertEquals("", endpoint.getRolesPUT());
        assertEquals("", endpoint.getRolesDELETE());
    }

}
