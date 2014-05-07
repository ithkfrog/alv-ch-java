package ch.alv.components.web.endpoint;

import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.web.endpoint.EndpointHelper} class.
 *
 * @since 1.0.0
 */
public class EndpointHelperTest {

    @Test
    public void testHelper() {

        List<HttpMethod> methods = new ArrayList<>();
        methods.add(HttpMethod.GET);
        methods.add(HttpMethod.POST);
        methods.add(HttpMethod.PUT);
        methods.add(HttpMethod.DELETE);

        assertEquals(methods, EndpointHelper.createAllMethodsList());

        List<HttpMethod> partialMethods = new ArrayList<>();
        partialMethods.add(HttpMethod.GET);
        partialMethods.add(HttpMethod.POST);

        assertEquals(partialMethods, EndpointHelper.createMethodList(HttpMethod.GET, HttpMethod.POST));

        assertEquals(new ArrayList<>(), EndpointHelper.createMethodList(null));
        assertEquals(new ArrayList<>(), EndpointHelper.createMethodList(new HttpMethod[0]));

    }

    @Test
    public void fullCoverageForStaticTest() {
        new EndpointHelper();
    }

}
