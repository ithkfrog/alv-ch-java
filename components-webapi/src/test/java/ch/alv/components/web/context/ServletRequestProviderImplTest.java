package ch.alv.components.web.context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.web.endpoint.SpringBeansEndpointProvider} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/web-context-provider-test.xml")
public class ServletRequestProviderImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private ServletRequestProvider provider;

    @Test
    public void testGetLanguage() {
        MockHttpServletRequest request = (MockHttpServletRequest) provider.getRequest();
        request.addParameter("language", "fr");
        assertEquals("fr", provider.getLanguage());
    }

}
