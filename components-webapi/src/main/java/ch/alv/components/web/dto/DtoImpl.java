package ch.alv.components.web.dto;

import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of the {@link ch.alv.components.web.dto.Dto}.
 *
 * @since 1.0.0
 */
public abstract class DtoImpl implements Dto {

    private List<Link> links = new ArrayList<>();

    private String id;

    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
