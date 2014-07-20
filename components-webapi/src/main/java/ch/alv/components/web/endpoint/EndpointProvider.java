package ch.alv.components.web.endpoint;

/**
 * An endpointProvider delivers registered endpoints by their name or type.
 *
 * @since 1.0.0
 */
public interface EndpointProvider {

    Endpoint getEndpointStore(String moduleName, String storeName);

    Endpoint getEndpointByResource(Class<?> resourceClass);

}
