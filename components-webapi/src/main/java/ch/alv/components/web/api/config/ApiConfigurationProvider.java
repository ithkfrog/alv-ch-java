package ch.alv.components.web.api.config;

/**
 * A (REST) resource configuration contains all required data to expose a
 * corresponding server endpoint. A ApiConfigurationProvider provides interested
 * components to have access to all resource configurations.
 *
 * @since 1.0.0
 */
public interface ApiConfigurationProvider {

    ApiConfiguration getConfiguration();

}
