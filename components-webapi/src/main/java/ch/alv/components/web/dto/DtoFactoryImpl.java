package ch.alv.components.web.dto;

import ch.alv.components.core.mapper.BeanMapper;
import ch.alv.components.core.model.ModelItem;
import ch.alv.components.web.endpoint.Endpoint;
import com.google.gson.Gson;

import javax.annotation.Resource;

/**
 * Default implementation of the {@link DtoFactory} interface.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class DtoFactoryImpl implements DtoFactory {

    @Resource
    private BeanMapper mapper;

    @Override
    public Dto createDtoFromRequestBody(String requestBody, Endpoint endpoint) {
        Gson gson = new Gson();
        return gson.fromJson(requestBody, endpoint.getDtoClass());
    }

    @Override
    public Dto createDtoFromEntity(ModelItem entity, Endpoint endpoint) {
        return mapper.mapObject(entity, endpoint.getDtoClass());
    }

}
