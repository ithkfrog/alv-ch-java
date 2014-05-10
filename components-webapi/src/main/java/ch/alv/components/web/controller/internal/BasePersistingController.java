package ch.alv.components.web.controller.internal;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.core.beans.mapper.BeanMapper;
import ch.alv.components.core.spring.ApplicationContextProvider;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.service.PersistenceService;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.endpoint.Endpoint;
import ch.alv.components.web.endpoint.EndpointRegistry;

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

    @Resource
    private EndpointRegistry endpointRegistry;

    public BasePersistingController(ApplicationContextProvider contextProvider) {
        super(contextProvider);
    }

    public Object persist(String moduleName, String storeName, String id, String body) {
        Endpoint endpoint = endpointRegistry.getEndpoint(moduleName, storeName);
        Dto dto = getDtoFactory().createDtoFromRequestBody(body, endpoint);
        ModelItem<String, Integer> entity = mapper.mapObject(dto, endpoint.getEntityClass());
        if (StringHelper.isNotEmpty(id)) {
            entity.setId(id);
        }
        entity = getService(endpoint).save(entity);
        return entity;
    }

    protected PersistenceService getService(Endpoint endpoint) {
        return getBean(endpoint.getServiceName());
    }

}
