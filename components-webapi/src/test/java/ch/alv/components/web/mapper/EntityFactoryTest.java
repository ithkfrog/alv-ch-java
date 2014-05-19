package ch.alv.components.web.mapper;

import ch.alv.components.service.RuntimeServiceLayerException;
import ch.alv.components.web.context.ServletRequestProvider;
import ch.alv.components.web.mock.MockEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test cases for the {@link ch.alv.components.web.endpoint.EndpointRegistry} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/entity-factory-test-context.xml")
public class EntityFactoryTest {

    public static final String REQUEST_URI = "/api/test/test";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private EntityFactory factory;

    private final Link link = new Link("path/testId", "self");

    @Resource
    private ServletRequestProvider requestProvider;

    @Test
    public void testSuccess() {
        ((MockHttpServletRequest) requestProvider.getRequest()).setRequestURI("/api/test/test");
        MockEntity entity = (MockEntity) factory.createBean(link, MockEntity.class, "ch.alv.components.web.mock.MockEntity");
        assertEquals("testId", entity.getId());
    }

    @Test
    public void testNoSuchEntity() {
        ((MockHttpServletRequest) requestProvider.getRequest()).setRequestURI("/api/test/test");
        exception.expect(RuntimeServiceLayerException.class);
        exception.expectMessage("Error while converting a Link to it's entity representation");
        factory.createBean(new Link("path/unknownId", "self"), MockEntity.class, "ch.alv.components.web.mock.MockEntity");
    }

    @Test
    public void testUnManagedClass() {
        ((MockHttpServletRequest) requestProvider.getRequest()).setRequestURI("/api/test/test");
        assertNull(factory.createBean(link, EntityFactoryTest.class, "ch.alv.components.web.mapper.EntityFactoryTest"));
    }

    @Test
    public void testUnknownClass() {
        ((MockHttpServletRequest) requestProvider.getRequest()).setRequestURI("/api/test/test");
        exception.expect(RuntimeServiceLayerException.class);
        exception.expectMessage("Error while converting a Link to it's entity representation");
        factory.createBean(link, MockEntity.class, "test.classes");
    }

    @Test
    public void testNullClass() {
        ((MockHttpServletRequest) requestProvider.getRequest()).setRequestURI(REQUEST_URI);
        exception.expect(RuntimeServiceLayerException.class);
        exception.expectMessage("Error while converting a Link to it's entity representation");
        factory.createBean(link, null, null);
    }
}
