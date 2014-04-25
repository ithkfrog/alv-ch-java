package ch.alv.components.data.jpa;

import ch.alv.components.data.jpa.test.TestEntity;
import ch.alv.components.data.jpa.test.TestRepository;
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
@ContextConfiguration(locations = "classpath:integration-test-applicationcontext.xml")
public class CustomSearchRepositoryImplIT {

    @Resource
    private TestRepository repository;

    @Test
    public void testUnParameterizedSearch() {
        Iterable<TestEntity> result = repository.findWithDefaultSearch(null);
        Iterator<TestEntity> it = result.iterator();
        int counter = 0;
        while (it.hasNext()) {
            TestEntity currentPos = it.next();
            Assert.assertEquals("testString", currentPos.getMyAttribute());
            counter++;
        }
        Assert.assertEquals(1, counter);
    }

}
