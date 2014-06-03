package ch.alv.components.web.dto;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.endpoint.DefaultEndpoint;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.mock.MockDto;
import ch.alv.components.web.mock.MockEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link DtoImpl} class
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/dto-factory-impl-test.xml")
public class DefaultDtoFactoryTest {

    @Resource
    private DtoFactory factory;

    @Resource
    private HttpServletRequest request;

    private Endpoint endpoint = new TestEndpoint();

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testDtoFromRequestBody() {
        String body = "{\"id\": \"testId\", \"version\": 66}";
        Dto dto = factory.createDtoFromRequestBody(body, endpoint);
        assertTrue(dto instanceof MockDto);
        assertEquals("testId", dto.getId());
        assertEquals((Integer) 66, dto.getVersion());
    }

    @Test
    public void testDtoFromNullRequestBody() {
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("requestBody param must not be empty.");
        assertNull(factory.createDtoFromRequestBody(null, endpoint));
    }

    @Test
    public void testDtoFromEmptyRequestBody() {
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("requestBody param must not be empty.");
        assertNull(factory.createDtoFromRequestBody("", endpoint));
    }

    @Test
    public void testDtoFromRequestBodyWithEmptyCurlyBraces() {
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("Illegal string source.");
        factory.createDtoFromRequestBody("{}", endpoint);
    }

    @Test
    public void testDtoFromRequestBodyWithoutCurlyBraces() {
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("Error while creating Dto of type ch.alv.components.web.mock.MockDto from requestBody 'tesa da '.");
        factory.createDtoFromRequestBody("tesa da ", endpoint);
    }

    @Test
    public void testDtoFromInvalidRequestBody() {
        exception.expect(DtoFactoryException.class);
        exception.expectMessage("Error while creating Dto of type ch.alv.components.web.mock.MockDto from requestBody 'tesa{ d}a '");
        factory.createDtoFromRequestBody("tesa{ d}a ", endpoint);
    }

    @Test
    public void testDtoFromRequestBodyWithNullEndpoint() {
        String body = "{\"id\": \"testId\", \"version\": 66}";
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Endpoint must not be null.");
        factory.createDtoFromRequestBody(body, null);
    }

    @Test
    public void testDtoFromEntity() {
        MockEntity entity = new MockEntity();
        entity.setId("testId");
        entity.setVersion(66);
        Dto dto = factory.createDtoFromEntity(entity, endpoint);
        assertTrue(dto instanceof MockDto);
        assertEquals("testId", dto.getId());
        assertEquals((Integer) 66, dto.getVersion());
    }

    @Test
    public void testDtoFromEntityWithLanguage() {
        ((MockHttpServletRequest) request).addParameter("language", "fr");
        MockEntity entity = new MockEntity();
        entity.setId("testId");
        entity.setVersion(66);
        Dto dto = factory.createDtoFromEntity(entity, endpoint);
        assertTrue(dto instanceof MockDto);
        assertEquals("testId", dto.getId());
        assertEquals((Integer) 66, dto.getVersion());
        assertTrue(dto.getLinks().get(0).getHref().contains("?language=fr"));
    }

    @Test
    public void testDtoFromNullEntity() {
        assertNull(factory.createDtoFromEntity(null, endpoint));
    }

    private class TestEndpoint extends DefaultEndpoint {

        @Override
        public String getModuleName() {
            return null;
        }

        @Override
        public String getStoreName() {
            return null;
        }

        @Override
        public Class<? extends Dto> getDtoClass() {
            return MockDto.class;
        }

        @Override
        public Class<? extends ModelItem> getEntityClass() {
            return MockEntity.class;
        }
    }

}
