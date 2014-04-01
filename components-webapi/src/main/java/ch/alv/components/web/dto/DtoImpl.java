package ch.alv.components.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of the {@link Dto}.
 *
 * @since 1.0.0
 */
public abstract class DtoImpl implements Dto {

    private List<Link> links = new ArrayList<>();

    @JsonIgnore
    private String id;

    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
