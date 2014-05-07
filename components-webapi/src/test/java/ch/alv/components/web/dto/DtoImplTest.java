package ch.alv.components.web.dto;

import ch.alv.components.web.mock.TestDto;
import org.junit.Test;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link ch.alv.components.web.dto.internal.DtoImpl} class
 *
 * @since 1.0.0
 */
public class DtoImplTest {


    @Test
    public void testDto() {
        TestDto dto = new TestDto();

        String id = "stringId";
        int version = 3;
        List<Link> links = new ArrayList<>();
        links.add(new Link("http://localhost.ch"));

        dto.setId(id);
        dto.setVersion(version);
        dto.setLinks(links);

        assertEquals(id, dto.getId());
        assertEquals((Integer) version, dto.getVersion());
        assertEquals(links, dto.getLinks());
    }

}
