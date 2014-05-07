package ch.alv.components.web.dto;

import org.springframework.hateoas.Link;

import java.util.List;

/**
 * Marker interface for Web-Api Dtos.
 *
 * @since 1.0.0
 */
public interface Dto {

    String getId();

    List<Link> getLinks();

    Integer getVersion();

}
