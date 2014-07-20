package ch.alv.components.web.api.config;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link HeaderParameter} class.
 *
 * @since 1.0.0
 */
public class QueryParameterCollectionTest {

    @Test
    public void testName() {
        QueryParameterCollection collection = new QueryParameterCollection();
        collection.setName("testName");
        assertEquals("testName", collection.getName());
    }

    @Test
    public void testParameters() {
        List<QueryParameter> list = new ArrayList<>();
        QueryParameterCollection collection = new QueryParameterCollection();
        collection.setParameters(list);
        assertEquals(list, collection.getParameters());
    }


}
