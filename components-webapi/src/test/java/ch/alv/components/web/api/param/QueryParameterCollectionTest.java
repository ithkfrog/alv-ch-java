package ch.alv.components.web.api.param;

import ch.alv.components.web.api.config.QueryParameter;
import ch.alv.components.web.api.config.QueryParameterCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.QueryParameter} class.
 *
 * @since 1.0.0
 */
public class QueryParameterCollectionTest {

    private static final String NAME = "paramsCollectionName";
    private static final List<QueryParameter> PARAMS = new ArrayList<>();
    private static final QueryParameterCollection COLLECTION = new QueryParameterCollection();

    @Before
    public void init() {
        COLLECTION.setName(NAME);
        COLLECTION.setParameters(PARAMS);
    }

    @Test
    public void testName() {
        assertEquals(NAME, COLLECTION.getName());
    }

    @Test
    public void testParameters() {
        PARAMS.add(new QueryParameter());
        PARAMS.add(new QueryParameter());
        PARAMS.add(new QueryParameter());
        assertEquals(3, PARAMS.size());
    }

}
