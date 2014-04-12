package ch.alv.components.web.controller;

import ch.alv.components.core.mapper.BeanMapper;
import ch.alv.components.core.model.ModelItem;
import ch.alv.components.service.persistence.PersistenceService;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

/**
 * Base implementation of a controller that persists data.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public abstract class BasePersistingController extends BaseController {

    @Resource
    protected BeanMapper mapper;

    public Object persist(String moduleName, String storeName, String id, String body) {
        Endpoint endpoint = EndpointRegistry.getEndpoint(moduleName, storeName);
        Dto dto = getDtoFactory().createDtoFromRequestBody(body, endpoint);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ModelItem<String, Integer> entity = mapper.mapObject(dto, endpoint.getEntityClass());
        entity.setId(id);
        entity = getService(endpoint).save(entity);
        return entity;
    }

    protected PersistenceService getService(Endpoint endpoint) {
        return getBean(endpoint.getServiceName());
    }

}
