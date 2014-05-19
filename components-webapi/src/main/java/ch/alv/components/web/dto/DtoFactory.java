package ch.alv.components.web.dto;


import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.web.endpoint.Endpoint;

/**
 * Factory to create Dtos.
 *
 * @since 1.0.0
 */
public interface DtoFactory {

    Dto createDtoFromRequestBody(String requestBody, Endpoint endpoint);

    Dto createDtoFromEntity(Identifiable entity, Endpoint endpoint);

}
