package ch.alv.components.iam.endpoint.dto;

import ch.alv.components.web.dto.internal.DtoImpl;

/**
 * WebApi dto for the {@link ch.alv.components.iam.model.Application} entity.
 *
 * @since 1.0.0
 */
public class ApplicationDto extends DtoImpl {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
