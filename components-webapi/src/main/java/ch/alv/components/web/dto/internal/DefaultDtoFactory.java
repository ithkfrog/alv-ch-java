package ch.alv.components.web.dto.internal;

import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.core.beans.mapper.BeanMapper;
import ch.alv.components.core.utils.StringHelper;
import ch.alv.components.web.context.ServletRequestProvider;
import ch.alv.components.web.dto.Dto;
import ch.alv.components.web.dto.DtoFactory;
import ch.alv.components.web.dto.DtoFactoryException;
import ch.alv.components.web.endpoint.Endpoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.Link;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of the {@link ch.alv.components.web.dto.DtoFactory} interface.
 *
 * @since 1.0.0
 */
public class DefaultDtoFactory implements DtoFactory {

    @Resource
    private BeanMapper mapper;

    @Resource
    private ServletRequestProvider requestProvider;

    @Override
    public Dto createDtoFromRequestBody(String requestBody, Endpoint endpoint) {
        if (StringHelper.isEmpty(requestBody)) {
            throw new IllegalArgumentException("requestBody param must not be empty.");
        }
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(requestBody);
        if (matcher.find()) {
            String value = matcher.group();
            value = value.substring(1);
            value = value.substring(0, value.length()-1);
            if (StringHelper.isEmpty(value)) {
                throw new IllegalArgumentException("Illegal string source.");
            }
        }
        if (endpoint == null) {
            throw new IllegalArgumentException("Endpoint must not be null.");
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(requestBody, endpoint.getDtoClass());
        } catch (IOException e) {
            throw new DtoFactoryException("Error while creating Dto of type " + endpoint.getDtoClass().getName()
                    + " from requestBody '" + requestBody + "'.", e);
        }
    }

    @Override
    public Dto createDtoFromEntity(ModelItem entity, Endpoint endpoint) {
        if (entity == null) {
            return null;
        }
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
