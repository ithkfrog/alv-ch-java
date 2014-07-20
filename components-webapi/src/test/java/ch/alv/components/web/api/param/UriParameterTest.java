package ch.alv.components.web.api.param;

import ch.alv.components.web.api.config.ParameterType;
import ch.alv.components.web.api.config.UriParameter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.web.api.config.UriParameter} class.
 *
 * @since 1.0.0
 */
public class UriParameterTest {

    public static final String NAME = "paramName";
    public static final String DESCRIPTION = "paramDescription";
    public static final String PATTERN = "{" + NAME + "}";
    public static final ParameterType TYPE = ParameterType.STRING;
    private UriParameter param = new UriParameter();

    @Before
    public void init() {
        param.setName(NAME);
        param.setDescription(DESCRIPTION);
        param.setPattern(PATTERN);
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
    public void testMarker() {
        assertEquals(PATTERN, param.getPattern());
    }

    @Test
    public void testType() {
        assertEquals(TYPE, param.getType());
    }

}
