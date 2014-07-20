package ch.alv.components.web.api.handler;

import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.service.data.PagingDataService;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.config.ResourceConfiguration;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

/**
 * Default implementation of the {@link DeleteRequestHandler} interface.
 *
 * @since 1.0.0
 */
public class DefaultDeleteRequestHandler extends BaseDefaultRequestHandler implements DeleteRequestHandler {

    @Resource
    private PagingDataService dataService;

    @Override
    @SuppressWarnings("unchecked")
    public Object handleRequest(HttpServletRequestWrapper request, ApiConfiguration apiConfiguration) throws ServiceLayerException, WebLayerException {
        ResourceConfiguration configuration = apiConfiguration.getResourceByUri(request.getRequest().getRequestURI());
        String[] requestUriTokens = request.getRequest().getRequestURI().split("/");
        String id = requestUriTokens[requestUriTokens.length - 1];
        Class<?> entityClass = getTargetEntity(configuration.getResourceType());
        Object obj = dataService.find(id, entityClass);
        if (obj == null) {
            throw new WebLayerException(configuration.getName() + " with id '" + id + "' not found.", HttpStatus.NOT_FOUND);
        }
        dataService.delete(id, entityClass);
        return new ResponseEntity<>("Resource with id '" + id + "' successfully deleted.", HttpStatus.OK);
    }

}
