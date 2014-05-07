package ch.alv.components.data.jpa;

import ch.alv.components.data.mock.JpaTestEntity;
import ch.alv.components.data.mock.JpaTestRepository;
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
@ContextConfiguration(locations = "classpath:spring/jpa-custom-search-repository-it-context.xml")
public class JpaCustomSearchRepositoryIT {

    @Resource
    private JpaTestRepository repository;


    @Test
    public void testUnParameterizedSearch() {
        Iterable<JpaTestEntity> result = repository.find(null);
        Iterator<JpaTestEntity> it = result.iterator();
        int counter = 0;
        while (it.hasNext()) {
            JpaTestEntity currentPos = it.next();
            Assert.assertEquals("testString", currentPos.getMyAttribute());
            counter++;
        }
        Assert.assertEquals(1, counter);
    }

}
