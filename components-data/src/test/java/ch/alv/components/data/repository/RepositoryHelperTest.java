package ch.alv.components.data.repository;

import org.junit.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link ch.alv.components.data.repository.RepositoryHelper} class
 *
 * @since 1.0.0
 */
public class RepositoryHelperTest {

    @Test
    public void testApplyPageableToList() {
        List<Integer> list = new ArrayList<>();
        assertEquals(list, RepositoryHelper.applyPageable(null, list));

        for (int i = 0; i < 99; i++) {
            list.add(i);
        }

        // all items should be included (case "limit - 1")
        assertEquals(99, RepositoryHelper.applyPageable(new PageRequest(0, 100), list).size());

        // second page should be empty.
        assertEquals(0, RepositoryHelper.applyPageable(new PageRequest(1, 100), list).size());

        // all items should be included (case "limit")
        list.add(99);
        assertEquals(100, RepositoryHelper.applyPageable(new PageRequest(0, 100), list).size());

        // only 100 items should be contained (case "limit + 1")
        list.add(100);
        assertEquals(100, RepositoryHelper.applyPageable(new PageRequest(0, 100), list).size());
        // but a second page should be provided.
        assertEquals(1, RepositoryHelper.applyPageable(new PageRequest(1, 100), list).size());

        // one single page for all if pageable is null
        assertEquals(101, RepositoryHelper.applyPageable(null, list).size());
    }

    @Test
    public void testApplyPageableToPage() {
        List<Integer> list = new ArrayList<>();
        assertEquals(list, RepositoryHelper.applyPageable(null, list));

        for (int i = 0; i < 99; i++) {
            list.add(i);
        }

        // all items should be included (case "limit - 1")
        assertEquals(99, RepositoryHelper.applyPageable(new PageRequest(0, 100), new PageImpl<>(list)).getNumberOfElements());

        // second page should be empty.
        assertEquals(0, RepositoryHelper.applyPageable(new PageRequest(1, 100), new PageImpl<>(list)).getNumberOfElements());

        // all items should be included (case "limit")
        list.add(99);
        assertEquals(100, RepositoryHelper.applyPageable(new PageRequest(0, 100), new PageImpl<>(list)).getNumberOfElements());

        // only 100 items should be contained (case "limit + 1")
        list.add(100);
        assertEquals(100, RepositoryHelper.applyPageable(new PageRequest(0, 100), new PageImpl<>(list)).getNumberOfElements());
        // but a second page should be provided.
        assertEquals(1, RepositoryHelper.applyPageable(new PageRequest(1, 100), new PageImpl<>(list)).getNumberOfElements());

        // one single page for all if pageable is null
        assertEquals(101, RepositoryHelper.applyPageable(null, new PageImpl<>(list)).getNumberOfElements());
    }

    @Test
    public void testCreatePage() {
        List<Integer> list = new ArrayList<>();
        assertEquals(list, RepositoryHelper.applyPageable(null, list));

        for (int i = 0; i < 99; i++) {
            list.add(i);
        }

        // all items should be included (case "limit - 1")
        assertEquals(99, RepositoryHelper.createPage(new PageRequest(0, 100), list).getNumberOfElements());

        // second page should be empty.
        assertEquals(0, RepositoryHelper.createPage(new PageRequest(1, 100), list).getNumberOfElements());

        // all items should be included (case "limit")
        list.add(99);
        assertEquals(100, RepositoryHelper.createPage(new PageRequest(0, 100), list).getNumberOfElements());

        // only 100 items should be contained (case "limit + 1")
        list.add(100);
        assertEquals(100, RepositoryHelper.createPage(new PageRequest(0, 100), list).getNumberOfElements());
        // but a second page should be provided.
        assertEquals(1, RepositoryHelper.createPage(new PageRequest(1, 100), list).getNumberOfElements());

        assertEquals(101, RepositoryHelper.createPage(null, list).getNumberOfElements());

        assertEquals(0, RepositoryHelper.createPage(null, null).getNumberOfElements());
    }

    @Test
    public void testConstructor() {
        new RepositoryHelper();
    }
}
