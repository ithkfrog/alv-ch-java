package ch.alv.components.web.mapper;

import ch.alv.components.core.model.ModelItem;
import ch.alv.components.core.spring.context.DefaultContextProvider;
import ch.alv.components.web.context.ServletRequestProvider;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import org.dozer.BeanFactory;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Converts modelItems to Links targeting the matching endpoint.
 *
 * @since 1.0.0
 */
@Component
public class LinkFactory implements BeanFactory {

    @Resource
    private ServletRequestProvider requestProvider;

    @Override
    public Object createBean(Object o, Class<?> aClass, String s) {
        Endpoint endpoint = EndpointRegistry.getEndpoint(aClass);
        if (endpoint == null) {
            return null;
        }
        return new Link(requestProvider.getBasePath() + endpoint.getModuleName() + "/" + endpoint.getStoreName() + "/" + ((ModelItem) o).getId(), "self");
    }

}