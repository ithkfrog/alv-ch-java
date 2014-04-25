package ch.alv.components.core.beans.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Unit tests for the BeanMapper
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mapper-test.xml")
public class DozerBeanMapperAdapterTest {

    @Autowired
    private BeanMapper mapper;

    @Test
    public void testMapToFreshObject() {
        String key = "testKey";
        String value = "testValue";
        BeanA a = new BeanA(key, value);
        BeanB b = mapper.mapObject(a, BeanB.class);
        Assert.assertEquals(key, b.getKey());
        Assert.assertEquals(value, b.getValue());
    }

    @Test
    public void testMapToExistingObject() {
        String key = "testKey";
        String value = "testValue";

        BeanA a = new BeanA(key, value);
        BeanB b = new BeanB("initialKey", "initialValue");

        mapper.mapObject(a, b);
        Assert.assertEquals(key, b.getKey());
        Assert.assertEquals(value, b.getValue());
    }

    @Test
    public void testMapCollection() {
        String keyA = "testKeyA";
        String valueA = "testValueA";

        String keyB = "testKeyB";
        String valueB = "testValueB";

        BeanA a = new BeanA(keyA, valueA);
        BeanA b = new BeanA(keyB, valueB);

        List<Object> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        List<BeanB> newList = mapper.mapCollection(list, BeanB.class);
        Assert.assertEquals(keyA, newList.get(0).getKey());
        Assert.assertEquals(valueA, newList.get(0).getValue());
        Assert.assertEquals(keyB, newList.get(1).getKey());
        Assert.assertEquals(valueB, newList.get(1).getValue());
    }

}
