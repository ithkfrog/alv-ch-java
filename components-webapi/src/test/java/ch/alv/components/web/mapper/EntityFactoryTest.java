package ch.alv.components.web.mapper;

import ch.alv.components.web.context.ServletRequestProvider;
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
@ContextConfiguration(locations = "classpath:spring/mapper-factories-tests.xml")
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
        MockFactoryTestEntity entity = (MockFactoryTestEntity) factory.createBean(link, MockFactoryTestEntity.class, "ch.alv.components.web.mapper.MockFactoryTestEntity");
        assertEquals("testId", entity.getId());
    }

    @Test
    public void testNoSuchEntity() {
        ((MockHttpServletRequest) requestProvider.getRequest()).setRequestURI("/api/test/test");
        exception.expect(IllegalStateException.class);
        exception.expectMessage("No entity of type ch.alv.components.web.mapper.MockFactoryTestEntity with id unknownId found.");
        factory.createBean(new Link("path/unknownId", "self"), MockFactoryTestEntity.class, "ch.alv.components.web.mapper.MockFactoryTestEntity");
    }

    @Test
    public void testUnManagedClass() {
        ((MockHttpServletRequest) requestProvider.getRequest()).setRequestURI("/api/test/test");
        assertNull(factory.createBean(link, EntityFactoryTest.class, "ch.alv.components.web.mapper.EntityFactoryTest"));
    }

    @Test
    public void testUnknownClass() {
        ((MockHttpServletRequest) requestProvider.getRequest()).setRequestURI("/api/test/test");
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Error while converting a Link to it's entity representation (entity not supported).");
        factory.createBean(link, MockFactoryTestEntity.class, "test.classes");
    }

    @Test
    public void testNullClass() {
        ((MockHttpServletRequest) requestProvider.getRequest()).setRequestURI(REQUEST_URI);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Error while converting a Link to it's entity representation (entity not supported).");
        factory.createBean(link, null, null);
    }
}
