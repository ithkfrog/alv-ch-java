package ch.alv.components.persistence.laboratory;

import ch.alv.components.persistence.testcommons.TestEntity;
import ch.alv.components.persistence.testcommons.TestRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Iterator;


/**
 * Unit tests for the Repositories
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:components-persistence-test-context.xml")
public class RepositoryLaborIT {

    @Resource
    private TestRepository repository;

    @Test
    public void testConnection() {
        TestEntity entity = new TestEntity();
        entity.setMyAttribute("hsql rocks again");
        repository.save(entity);
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
