package ch.alv.components.web.api.param;

import ch.alv.components.web.api.config.ParameterType;
import ch.alv.components.web.api.config.QueryParameter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.QueryParameter} class.
 *
 * @since 1.0.0
 */
public class QueryParameterTest {

    public static final String NAME = "paramName";
    public static final String DESCRIPTION = "paramDescription";
    public static final boolean REQUIRED = true;
    public static final ParameterType TYPE = ParameterType.STRING;
    private QueryParameter param = new QueryParameter();

    @Before
    public void init() {
        param.setName(NAME);
        param.setDescription(DESCRIPTION);
        param.setRequired(REQUIRED);
        param.setType(TYPE);
    }

    @Test
    public void testName() {
        assertEquals(NAME, param.getName());
    }

    @Test
    public void testDescription() {
        assertEquals(DESCRIPTION, param.getDescription());
    }

    @Test
    public void testRequired() {
        assertEquals(REQUIRED, param.isRequired());
    }

    @Test
    public void testType() {
        assertEquals(TYPE, param.getType());
    }

}
