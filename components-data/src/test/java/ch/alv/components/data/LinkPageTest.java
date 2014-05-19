package ch.alv.components.data;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.data.DataLayerException}
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class LinkPageTest {

    private final List items = new ArrayList();

    @Before
    public void init() {
        items.add("test 1");
        items.add("test 2");
        items.add("test 3");
    }

    @Test
    public void testListConstructor() throws DataLayerException {
        Page page = new LinkPage(items);
        assertEquals(3, page.getContent().size());
    }

    @Test
    public void testPageConstructor() throws DataLayerException {
        Pageable pageable = new PageRequest(0, 2);
        Page sourcePage = new PageImpl(items, pageable, 3);
        Page page = new LinkPage(sourcePage);
        assertEquals(3, page.getContent().size());
    }

    @Test
    public void testComplexConstructor() throws DataLayerException {
        Pageable pageable = new PageRequest(0, 2);
        Page page = new LinkPage(items, pageable, 3);
        assertEquals(3, page.getContent().size());
    }

    @Test
    public void testAddLinkAndGetLinks() throws DataLayerException {

        LinkPage page = new LinkPage(items);
        page.addLink(null);
        page.addLink(new Link("http:test", "self"));
        assertEquals(1, page.getLinks().size());
        assertEquals("http:test", ((Link) page.getLinks().get(0)).getHref());
        assertEquals("self", ((Link) page.getLinks().get(0)).getRel());
    }

    @Test
    public void testSetLinks() throws DataLayerException {
        LinkPage page = new LinkPage(items);

        List<Link> links = new ArrayList<>();
        links.add(new Link("http:test", "self"));
        page.setLinks(links);

        assertEquals(1, page.getLinks().size());
        assertEquals("http:test", ((Link) page.getLinks().get(0)).getHref());
        assertEquals("self", ((Link) page.getLinks().get(0)).getRel());
    }

}
