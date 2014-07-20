package ch.alv.components.web.api.handler;

import ch.alv.components.web.api.mock.MockResource;
import ch.alv.components.web.api.mock.MockTargetedResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link BaseDefaultRequestHandler} class.
 *
 * @since 1.0.0
 */
public class BaseDefaultRequestHandlerTest {

    @Test
    public void testGetTargetEntity() {
        BaseDefaultRequestHandler helper = new BaseDefaultRequestHandlerTestHelper();
        assertEquals(MockResource.class, helper.getTargetEntity(MockResource.class));
        assertEquals(MockResource.class, helper.getTargetEntity(MockTargetedResource.class));
    }

    public class BaseDefaultRequestHandlerTestHelper extends BaseDefaultRequestHandler {}

}
