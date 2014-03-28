package ch.alv.components.persistence.repository;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;


/**
 * Unit tests for the Repositories
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-test.xml")
public class RepositoryLaborTest {

    @Resource
    private TestRepository repository;

    @Test
    @Transactional
    public void testConnection() {
        Iterable<TestEntity> result = repository.findAll();
        Iterator<TestEntity> it = result.iterator();
        int counter = 0;
        while (it.hasNext()) {
            it.next();
            counter++;
        }
        Assert.assertEquals(1, counter);
    }

}
