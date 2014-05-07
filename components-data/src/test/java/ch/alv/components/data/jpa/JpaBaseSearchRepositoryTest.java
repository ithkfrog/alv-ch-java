package ch.alv.components.data.jpa;

import ch.alv.components.data.mock.JpaBaseRepositoryTestEntity;
import ch.alv.components.data.mock.JpaBaseRepositoryTestRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Unit tests for the Repositories
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/jpa-base-search-repository-test-context.xml")
public class JpaBaseSearchRepositoryTest {

    @Resource
    private JpaBaseRepositoryTestRepository repository;

    private JpaBaseRepositoryTestEntity entity;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        entity = new JpaBaseRepositoryTestEntity();
        entity.setMyAttribute("TestAttributeValue");
        entity = repository.save(entity);
    }

    @Test
    public void testFindOne() {
        JpaBaseRepositoryTestEntity found = repository.findOne(entity.getId());
        assertEquals(entity.getId(), found.getId());
    }

    @Test
    public void testFindAll() {
        Page<JpaBaseRepositoryTestEntity> result = repository.getAll();
        Iterator<JpaBaseRepositoryTestEntity> it = result.iterator();
        int counter = 0;
        while (it.hasNext()) {
            JpaBaseRepositoryTestEntity currentPos = it.next();
            assertEquals("TestAttributeValue", currentPos.getMyAttribute());
            counter++;
        }
        assertTrue(0 < counter);
    }

    @Test
    public void testFindAllWithPageable() {
        Pageable pageable = new PageRequest(0, 100);
        Iterable<JpaBaseRepositoryTestEntity> result = repository.getAll(pageable);
        Iterator<JpaBaseRepositoryTestEntity> it = result.iterator();
        int counter = 0;
        while (it.hasNext()) {
            JpaBaseRepositoryTestEntity currentPos = it.next();
            assertEquals("TestAttributeValue", currentPos.getMyAttribute());
            counter++;
        }
        assertTrue(0 < counter);
    }

    @Test
    public void testFindAllWithIds() {
        List<String> ids = new ArrayList<>();
        ids.add(entity.getId());
        Iterable<JpaBaseRepositoryTestEntity> result = repository.getAll(ids);
        Iterator<JpaBaseRepositoryTestEntity> it = result.iterator();
        int counter = 0;
        while (it.hasNext()) {
            JpaBaseRepositoryTestEntity currentPos = it.next();
            assertEquals("TestAttributeValue", currentPos.getMyAttribute());
            counter++;
        }
        assertTrue(0 < counter);
    }

    @Test
    public void testFindAllWithNonExistingIds() {
        List<String> ids = new ArrayList<>();
        ids.add("test");
        Iterable<JpaBaseRepositoryTestEntity> result = repository.getAll(ids);
        Iterator<JpaBaseRepositoryTestEntity> it = result.iterator();
        int counter = 0;
        while (it.hasNext()) {
            JpaBaseRepositoryTestEntity currentPos = it.next();
            assertEquals("TestAttributeValue", currentPos.getMyAttribute());
            counter++;
        }
        assertEquals(0, counter);
    }

    @Test
    public void testFindWithEmptySearchName() {
        assertNotNull(repository.find(null, ""));
    }

    @Test
    public void testFindWithNullSearch() {
        assertNotNull(repository.find(null, "baseSearchRepositoryNullSearch"));
    }

    @Test
    public void testFindWithNonStringSearch() {
        exception.expect(InvalidDataAccessApiUsageException.class);
        exception.expectMessage("Parameter 'search' must be a jpa query string.");
        assertNotNull(repository.find(null, "baseSearchRepositoryNonStringSearch"));
    }



}
