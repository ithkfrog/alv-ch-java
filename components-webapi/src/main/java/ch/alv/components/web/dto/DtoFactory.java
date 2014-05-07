package ch.alv.components.web.dto;


import ch.alv.components.core.beans.ModelItem;
import ch.alv.components.web.endpoint.Endpoint;

/**
 * Factory to create Dtos.
 *
 * @since 1.0.0
 */
public interface DtoFactory {

    Dto createDtoFromRequestBody(String requestBody, Endpoint endpoint);

    Dto createDtoFromEntity(ModelItem entity, Endpoint endpoint);

}
