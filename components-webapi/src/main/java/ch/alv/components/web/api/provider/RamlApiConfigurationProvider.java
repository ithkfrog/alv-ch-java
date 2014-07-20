package ch.alv.components.web.api.provider;

import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.config.ApiConfigurationProvider;
import org.raml.model.Raml;
import org.raml.parser.visitor.RamlDocumentBuilder;

import javax.annotation.PostConstruct;

/**
 * Raml based implementation of the {@link ch.alv.components.web.api.config.ApiConfigurationProvider} interface.
 *
 * @since 1.0.0
 */
public class RamlApiConfigurationProvider implements ApiConfigurationProvider {

    private final String ramlFile;

    private ApiConfiguration configuration = null;

    private final RamlConverter converter;

    public RamlApiConfigurationProvider(String ramlFile) {
        this.ramlFile = ramlFile;
        this.converter = new RamlConverter();
    }

    public RamlApiConfigurationProvider(String ramlFile, RamlConverter converter) {
        this.ramlFile = ramlFile;
        if (converter == null) {
            this.converter = new RamlConverter();
        } else {
            this.converter = converter;
        }
    }

    @PostConstruct
    private void init() {
        if (StringHelper.isEmpty(ramlFile)) {
            return;
        }
        Raml raml = new RamlDocumentBuilder().build(ramlFile);
        configuration = converter.convertRamlToInternalConfiguration(raml);
    }

    @Override
    public ApiConfiguration getConfiguration() {
        return configuration;
    }

}
