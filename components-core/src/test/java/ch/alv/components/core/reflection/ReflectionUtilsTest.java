package ch.alv.components.core.reflection;

import ch.alv.components.core.utils.ReflectionUtils;
import ch.alv.components.core.utils.ReflectionUtilsException;
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
        Assert.assertEquals(String.class, ReflectionUtils.determineFirstGenericParam(ExtendingBean.class));
    }

    @Test
    public void failDetermineFirstParameterNoSuperClass() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("ch.alv.components.core.reflection.NonExtendingBean must extend a parameterized superclass...");
        ReflectionUtils.determineFirstGenericParam(NonExtendingBean.class);
    }

    @Test
    public void failDetermineFirstParameterNullArg() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("Param 'classUnderExamination' must not be null!");
        ReflectionUtils.determineFirstGenericParam(null);
    }

    @Test
    public void failDetermineFirstParameterUnparameterizedSuperClass() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("Class: ch.alv.components.core.reflection.ExtendingBeanNonGeneric must extend a parameterized superclass...");
        ReflectionUtils.determineFirstGenericParam(ExtendingBeanNonGeneric.class);
    }

    @Test
    public void testDetermineParameterClassOfParameterizedSuperClass() throws ReflectionUtilsException {
        Assert.assertEquals(String.class, ReflectionUtils.determineGenericParam(ExtendingBean.class, 0));
        Assert.assertEquals(Long.class, ReflectionUtils.determineGenericParam(ExtendingBean.class, 1));
        Assert.assertEquals(Integer.class, ReflectionUtils.determineGenericParam(ExtendingBean.class, 2));
    }

    @Test
    public void testFailDetermineParameterClassOfParameterizedSuperClass() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("No Type Parameter defined for: 'ch.alv.components.core.reflection.ParameterizedSuperClass<java.lang.String, java.lang.Long, java.lang.Integer>' with index '4'");
        ReflectionUtils.determineGenericParam(ExtendingBean.class, 4);
    }

    @Test
    public void failDetermineParameterClassOfParameterizedSuperClassNullTarget() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("Param 'classUnderExamination' must not be null!");
        ReflectionUtils.determineGenericParam(null, 0);
    }

    @Test
    public void failDetermineParameterClassOfParameterizedSuperClassIndexBelowZero() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("Param 'index' must not be lower than 0!");
        ReflectionUtils.determineGenericParam(ExtendingBean.class, -1);
    }

    @Test
    public void failDetermineParameterClassOfParameterizedSuperClassTarget() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("Can't resolve a parameter class on type Class.class");
        ReflectionUtils.determineGenericParam(Class.class, 0);
    }

    @Test
    public void fullCoverageForStaticTest() throws ReflectionUtilsException {
        new ReflectionUtils();
    }

}
