package ch.alv.components.web.response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

/**
 * Extension of PageImpl that provides a list of links.
 *
 * @since 1.0.0
 */
public class LinkPageImpl<T> extends PageImpl<T> {

    private List<Link> links = new ArrayList<>();

    public LinkPageImpl(List<T> content) {
        super(content);
    }

    public LinkPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public LinkPageImpl(Page<T> page) {
        super(page.getContent(), new PageRequest(page.getNumber(), page.getSize()), page.getTotalElements());
    }

    public void addLink(Link link) {
        if (link != null) {
            links.add(link);
        }
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
