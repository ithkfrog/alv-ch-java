package ch.alv.components.web.mapper;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.spring.context.DefaultContextProvider;
import ch.alv.components.web.context.ContextPathProvider;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import org.dozer.BeanFactory;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

/**
 * Converts modelItems to Links targeting the matching endpoint.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
@Component
public class LinkFactory implements BeanFactory {

    @Override
    public Object createBean(Object o, Class<?> aClass, String s) {
        Endpoint endpoint = EndpointRegistry.getEndpoint(aClass);
        if (endpoint == null) {
            return null;
        }
        ContextPathProvider contextPathProvider = DefaultContextProvider.getBeanByName("contextPathProvider");
        return new Link(contextPathProvider.getContextPath() + "/" + endpoint.getModuleName() + "/" + endpoint.getStoreName() + "/" + ((ModelItem) o).getId(), "self");
    }

}