package ch.alv.components.core.spring;

import ch.alv.components.core.mapper.BeanMapper;
import ch.alv.components.core.spring.context.DefaultContextProvider;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Unit tests for the BeanMapper
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:default-context-provider-test.xml")
public class DefaultContextProviderTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetBeanByName() {
        BeanMapper mapper = DefaultContextProvider.getBeanByName("mapper");
        Assert.assertNotNull(mapper);
        Assert.assertTrue(mapper instanceof BeanMapper);
    }

    @Test
    public void testGetBeanByNameException() {
        String beanName = "nonExistingBean";
        exception.expect(NoSuchBeanDefinitionException.class);
        exception.expectMessage("No bean named '" + beanName + "' is defined");
        BeanMapper mapper = DefaultContextProvider.getBeanByName(beanName);
    }

}
