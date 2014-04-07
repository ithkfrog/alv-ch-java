package ch.alv.components.core.reflection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * Unit tests for the ReflectionUtils
 *
 * @since 1.0.0
 */
public class ReflectionUtilsTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testDetermineFirstParameter() throws ReflectionUtilsException {
        Assert.assertEquals(String.class, ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(ExtendingBean.class));
    }

    @Test
    public void failDetermineFirstParameterNoSuperClass() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("ch.alv.components.core.reflection.NonExtendingBean must extend a parameterized superclass...");
        ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(NonExtendingBean.class);
    }

    @Test
    public void failDetermineFirstParameterUnparameterizedSuperClass() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("Class: ch.alv.components.core.reflection.ExtendingBeanNonGeneric must extend a parameterized superclass...");
        ReflectionUtils.determineFirstParameterClassOfParameterizedSuperClass(ExtendingBeanNonGeneric.class);
    }

    @Test
    public void testDetermineParameterClassOfParameterizedSuperClass() throws ReflectionUtilsException {
        Assert.assertEquals(String.class, ReflectionUtils.determineParameterClassOfParameterizedSuperClass(ExtendingBean.class, 0));
        Assert.assertEquals(Long.class, ReflectionUtils.determineParameterClassOfParameterizedSuperClass(ExtendingBean.class, 1));
        Assert.assertEquals(Integer.class, ReflectionUtils.determineParameterClassOfParameterizedSuperClass(ExtendingBean.class, 2));
    }

    @Test
    public void testFailDetermineParameterClassOfParameterizedSuperClass() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("No Type Parameter define for: 'ch.alv.components.core.reflection.ParameterizedSuperClass<java.lang.String, java.lang.Long, java.lang.Integer>' with index '4'");
        ReflectionUtils.determineParameterClassOfParameterizedSuperClass(ExtendingBean.class, 4);
    }

}
