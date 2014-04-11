package ch.alv.components.web.mapper;

import ch.alv.components.service.common.ServiceRegistry;
import ch.alv.components.service.persistence.PersistenceService;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import org.dozer.BeanFactory;
import org.springframework.hateoas.Link;

import javax.annotation.Resource;

/**
 * Fetches the related entity with help of the matching {@link ch.alv.components.service.persistence.PersistenceService}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class EntityFactory implements BeanFactory {

    @Resource
    private ServiceRegistry serviceRegistry;

    @Override
    public Object createBean(Object o, Class<?> aClass, String s) {
        try {
            Class targetClass = Class.forName(s);
            Endpoint endpoint = EndpointRegistry.getEndpoint(targetClass);
            if (endpoint == null) {
                return null;
            }
            PersistenceService service = serviceRegistry.getPersistenceService(endpoint.getServiceName());
            String[] tokens = ((Link) o).getHref().split("/");
            Object target;
            target = service.getById(tokens[tokens.length - 1]);
            if (target == null) {
                throw new IllegalStateException("No entity of type " + s + " with id " + tokens[tokens.length - 1] + " found.");
            }
            return target;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Error while converting a Link to it's entity representation.");
        }
    }

}