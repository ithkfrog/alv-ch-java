package ch.alv.components.web.mapper;

import ch.alv.components.service.RuntimeServiceLayerException;
import ch.alv.components.service.data.DataService;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import org.dozer.BeanFactory;
import org.springframework.hateoas.Link;

import javax.annotation.Resource;

/**
 * Fetches the related entity with help of the matching {@link ch.alv.components.service.data.DataService}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class EntityFactory implements BeanFactory {

    @Resource
    private DataService service;

    @Resource
    private EndpointRegistry endpointRegistry;

    @Override
    public Object createBean(Object o, Class<?> aClass, String s) {
        try {
            Class targetClass = Class.forName(s);
            Endpoint endpoint = endpointRegistry.getEndpoint(targetClass);
            if (endpoint == null) {
                return null;
            }
            String[] tokens = ((Link) o).getHref().split("/");
            Object target;
            target = service.find(tokens[tokens.length - 1], targetClass);
            if (target == null) {
                throw new IllegalStateException("No entity of type " + s + " with id " + tokens[tokens.length - 1] + " found.");
            }
            return target;
        } catch (Exception e) {
            throw new RuntimeServiceLayerException("Error while converting a Link to it's entity representation", e);
        }
    }

}