package ch.alv.components.persistence.repository;

import ch.alv.components.persistence.testcommons.TestEntity;
import ch.alv.components.persistence.testcommons.TestRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
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
public class CustomRepositoryImplSimpleSearchTest {

    @Resource
    private TestRepository repository;

    @Test
    public void testUnParameterizedSearch() {
        String[] attributes = { "b", "a" };
        TestEntity hsqlEntity = new TestEntity();
        hsqlEntity.setMyAttribute(attributes[0]);
        repository.save(hsqlEntity);

        TestEntity mysqlEntity = new TestEntity();
        mysqlEntity.setMyAttribute(attributes[1]);
        repository.save(mysqlEntity);

        Iterable<TestEntity> result = repository.find(new PageRequest(0, 100), null);
        Iterator<TestEntity> it = result.iterator();
        int counter = 0;
        while (it.hasNext()) {
            TestEntity currentPos = it.next();
            Assert.assertEquals(attributes[counter], currentPos.getMyAttribute());
            counter++;
        }
        Assert.assertEquals(2, counter);
    }

}
