package ch.alv.components.web.api.handler;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.beans.mapper.BeanMapper;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.ServiceLayerException;
import ch.alv.components.service.data.PagingDataService;
import ch.alv.components.web.WebLayerException;
import ch.alv.components.web.api.config.ApiConfiguration;
import ch.alv.components.web.api.config.ResourceConfiguration;
import ch.alv.components.web.api.request.HttpServletRequestWrapper;
import ch.alv.components.web.api.utils.RequestHandlerUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Default implementation of the {@link PostRequestHandler} interface.
 *
 * @since 1.0.0
 */
public class DefaultPostRequestHandler extends BaseDefaultRequestHandler implements PostRequestHandler {

    @Resource
    private PagingDataService dataService;

    @Resource
    private BeanMapper mapper;

    @Override
    public Object handleRequest(HttpServletRequestWrapper wrapper, ApiConfiguration apiConfiguration) throws WebLayerException, IOException, ServiceLayerException {
        String line = RequestHandlerUtils.getRequestBodyAsString(wrapper);
        ObjectMapper jacksonMapper = new ObjectMapper();
        if (StringHelper.isNotEmpty(line)) {
            ResourceConfiguration configuration = apiConfiguration.getResourceByUri(wrapper.getRequest().getRequestURI());
            Class<?> entityClass = getTargetEntity(configuration.getResourceType());
            Object obj = dataService.save((Identifiable) jacksonMapper.readValue(line, entityClass), entityClass);
            if (entityClass == configuration.getResourceType()) {
                return new ResponseEntity<>(obj, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(mapper.mapObject(obj, configuration.getResourceType()), HttpStatus.OK);
            }
        }
        throw new WebLayerException("Could not post resource.", HttpStatus.BAD_REQUEST);
    }

}
