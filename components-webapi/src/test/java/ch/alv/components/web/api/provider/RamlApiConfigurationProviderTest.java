package ch.alv.components.web.api.provider;

import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.http.HttpProtocol;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link RamlApiConfigurationProvider} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/raml-api-configuration-provider-test-context.xml")
public class RamlApiConfigurationProviderTest {

    @Resource(name = "defaultProvider")
    private RamlApiConfigurationProvider provider;

    @Resource(name = "emptyFileProvider")
    private RamlApiConfigurationProvider emptyFileProvider;

    @Resource(name = "emptyConverterProvider")
    private RamlApiConfigurationProvider emptyConverterProvider;

    private ApiConfiguration configuration;

    @Before
    public void init() {
        configuration = provider.getConfiguration();
    }

    @Test
    public void testConfigurationValues() {
        assertEquals("Job-Room API", configuration.getTitle());
        assertEquals("v1.0", configuration.getVersion());
        assertEquals("http://localhost:8080/api/v1.0", configuration.getBaseUri());
        assertEquals("application/json", configuration.getMediaType());
        List<HttpProtocol> protocolList = configuration.getProtocols();
        assertEquals(2, protocolList.size());
        assertTrue(protocolList.contains(HttpProtocol.HTTP));
        assertTrue(protocolList.contains(HttpProtocol.HTTPS));

        Map<String, String> schemas = configuration.getSchemas();
        assertTrue(schemas.containsKey("auxCode"));
    }

    @Test
    public void testEmptyFileProvider() {
        assertNull(emptyFileProvider.getConfiguration());
    }

    @Test
         public void testEmptyConverterProvider() {
        // use default converter, if constructor arg is null
        assertEquals("Job-Room API", emptyConverterProvider.getConfiguration().getTitle());
    }

    @Test
    public void testConfiguredProvider() {
        // use default converter, if constructor arg is null
        assertEquals("Job-Room API", emptyConverterProvider.getConfiguration().getTitle());
    }
}
