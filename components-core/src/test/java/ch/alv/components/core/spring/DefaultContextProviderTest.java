package ch.alv.components.core.spring;

import ch.alv.components.core.beans.mapper.BeanMapper;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Unit tests for the BeanMapper
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/default-context-provider-test-context.xml")
public class DefaultContextProviderTest {

    @Resource
    private ApplicationContextProvider contextProvider;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetBeanByName() {
        BeanMapper mapper = contextProvider.getBeanByName("mapper");
        Assert.assertNotNull(mapper);
    }

    @Test
    public void testGetBeanByNameException() {
        String beanName = "nonExistingBean";
        exception.expect(NoSuchBeanDefinitionException.class);
        exception.expectMessage("No bean named '" + beanName + "' is defined");
        contextProvider.getBeanByName(beanName);
    }

    @Test
    public void testGetBeansOfType() {
        Map<String, BeanMapper> mappers = contextProvider.getBeansOfType(BeanMapper.class);
        assertEquals(1, mappers.size());
    }

}
