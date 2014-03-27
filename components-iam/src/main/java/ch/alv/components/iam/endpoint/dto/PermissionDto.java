package ch.alv.components.iam.endpoint.dto;

import ch.alv.components.web.dto.DtoImpl;

/**
 * WebApi dto for the {@link ch.alv.components.iam.model.Permission} entity.
 *
 * @since 1.0.0
 */
public class PermissionDto extends DtoImpl {

    private String name;

    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
