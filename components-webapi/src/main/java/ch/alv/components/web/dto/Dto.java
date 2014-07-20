package ch.alv.components.web.dto;

import ch.alv.components.core.beans.Identifiable;
import ch.alv.components.core.beans.Versionable;
import org.springframework.hateoas.Link;

import java.util.List;

/**
 * Marker interface for Web-Api Dtos.
 *
 * @since 1.0.0
 */
public interface Dto extends Identifiable<String>, Versionable<Integer> {

    List<Link> getLinks();

}
