package ch.alv.components.web.endpoint.filter;

import ch.alv.components.web.endpoint.filter.internal.DefaultEndpointHttpMethodFilter;
import ch.alv.components.web.endpoint.filter.internal.EndpointHttpMethodFilterResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.web.endpoint.filter.internal.DefaultSecurityFilter} unit.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/endpoint-filter-test.xml")
public class EndpointHttpMethodFilterImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    DefaultEndpointHttpMethodFilter filter;

    @Test
    public void testAllowedMethod() {
        EndpointHttpMethodFilterResult result = filter.doFilter("GET", "filterTestModule", "filterTestStore");
        assertEquals(EndpointHttpMethodFilterResult.OK, result.getResult());
        assertEquals("Method allowed.", result.getMessage());
    }

    @Test
    public void testUnknownEndpoint() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("No endpoint for store 'module/store' found.");
        EndpointHttpMethodFilterResult result = filter.doFilter("POST", "module", "store");
        assertEquals(EndpointHttpMethodFilterResult.NOK, result.getResult());
        assertEquals("Method POST not allowed for store 'filterTestModule/filterTestStore'.", result.getMessage());
    }

    @Test
    public void testUnAllowedPost() {
        exception.expect(UnSupportedMethodException.class);
        filter.doFilter("POST", "filterTestModule", "filterTestStore");
    }

    @Test
    public void testUnAllowedPut() {
        exception.expect(UnSupportedMethodException.class);
        filter.doFilter("PUT", "filterTestModule", "filterTestStore");
    }

    @Test
    public void testUnAllowedDelete() {
        exception.expect(UnSupportedMethodException.class);
        filter.doFilter("DELETE", "filterTestModule", "filterTestStore");
    }

    @Test
    public void testEmptyMethod() {
        exception.expect(UnSupportedMethodException.class);
        filter.doFilter("", "filterTestModule", "filterTestStore");
    }
}
