package ch.alv.components.data.internal;

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

    private static final long serialVersionUID = -2522202576062015408L;

    private List<Link> links = new ArrayList<>();

    public LinkPageImpl(List<T> content) {
        super(content);
    }

    public LinkPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public LinkPageImpl(PageImpl<T> page) {
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
