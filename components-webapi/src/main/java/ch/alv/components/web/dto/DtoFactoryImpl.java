package ch.alv.components.web.dto;

import ch.alv.components.core.beans.mapper.BeanMapper;
import ch.alv.components.core.model.ModelItem;
import ch.alv.components.web.context.ServletRequestProvider;
import ch.alv.components.web.endpoint.Endpoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Default implementation of the {@link DtoFactory} interface.
 *
 * @since 1.0.0
 */
public class DtoFactoryImpl implements DtoFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DtoFactoryImpl.class);

    @Resource
    private BeanMapper mapper;

    @Resource
    private ServletRequestProvider requestProvider;

    @Override
    public Dto createDtoFromRequestBody(String requestBody, Endpoint endpoint) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(requestBody, endpoint.getDtoClass());
        } catch (IOException e) {
            LOG.error("Error while creating Dto of type " + endpoint.getDtoClass().getName()
                    + " from requestBody " + requestBody, e);
            return null;
        }
    }

    @Override
    public Dto createDtoFromEntity(ModelItem entity, Endpoint endpoint) {
        Dto dto = mapper.mapObject(entity, endpoint.getDtoClass());
        dto.getLinks().add(new Link(createURL(entity, endpoint)));
        return dto;
    }

    private String createURL(ModelItem entity, Endpoint endpoint) {
        String languageSuffix = "";
        if (requestProvider.getLanguage() != null) {
            languageSuffix = "?language=" + requestProvider.getLanguage();
        }
        return requestProvider.getBasePath() + endpoint.getModuleName() + "/" + endpoint.getStoreName() + "/" + entity.getId() + languageSuffix;
    }
}
