package ch.alv.components.web.api.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link UriParamExtractor} class.
 *
 * @since 1.0.0
 */
public class UriParamExtractorTest {

    private static final String ID_URI_PATTERN = "/test/[test]/[testId]";
    private static final String NON_ID_URI_PATTERN = "/test/[test]/[another]";
    private static final String ILLEGAL_URI_PATTERN = "/test/[test/[another";
    private static final String URI = "/test/part/99";

    UriParamExtractor successfulExtractor = new UriParamExtractor(ID_URI_PATTERN);
    UriParamExtractor failingExtractor = new UriParamExtractor(NON_ID_URI_PATTERN);
    UriParamExtractor illegalExtractor = new UriParamExtractor(ILLEGAL_URI_PATTERN);

    @Before
    public void init() {
        successfulExtractor.setStartMarker("[");
        successfulExtractor.setEndMarker("]");
        successfulExtractor.setIdParamNames(new String[] { "id", "itemId", "testId" } );
        failingExtractor.setStartMarker("[");
        failingExtractor.setEndMarker("]");
        illegalExtractor.setStartMarker("[");
        illegalExtractor.setEndMarker("]");
    }

    @Test
    public void testStartAndEndMarker() {
        assertEquals("[", successfulExtractor.getStartMarker());
        assertEquals("]", successfulExtractor.getEndMarker());
    }

    @Test
    public void testIdParamNames() {
        assertEquals(3, successfulExtractor.getIdParamNames().length);
    }

    @Test
    public void testGuessIdParam() {
        assertNotNull("99", successfulExtractor.guessIdParam(URI).getValue());
        assertNull(failingExtractor.guessIdParam(URI));
    }

    @Test
    public void testExtractParam() {
        assertNotNull("part", successfulExtractor.extractParam("test", URI));
        assertNull(successfulExtractor.extractParam("unknown", URI));
        assertNull(successfulExtractor.extractParam("unknown", null));
        assertNull(successfulExtractor.extractParam("test", "a"));
        assertNull(illegalExtractor.extractParam("unknown", URI));
    }

    @Test
    public void testExtractParams() {
        Map<String, String[]> params = successfulExtractor.extractParams(URI);
        assertEquals(2, params.size());
        assertTrue(params.containsKey("test"));
        assertTrue(params.containsKey("testId"));
        assertTrue(successfulExtractor.extractParams(null).size() == 0);
        assertTrue(successfulExtractor.extractParams("a").size() == 0);
        assertTrue(illegalExtractor.extractParams(URI).size() == 0);
        assertTrue(illegalExtractor.extractParams(ILLEGAL_URI_PATTERN).size() == 0);
        assertTrue(new UriParamExtractor("/{}").extractParams("/test/test/test").size() == 0);
    }

}
