package ch.alv.components.core.utils;

import ch.alv.components.core.mock.MockReflectionUtilsExtendingBean;
import ch.alv.components.core.mock.MockReflectionUtilsExtendingBeanNonGeneric;
import ch.alv.components.core.mock.MockReflectionUtilsNonExtendingBean;
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
        Assert.assertEquals(String.class, ReflectionUtils.determineFirstGenericParam(MockReflectionUtilsExtendingBean.class));
    }

    @Test
    public void failDetermineFirstParameterNoSuperClass() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("Class: ch.alv.components.core.mock.MockReflectionUtilsNonExtendingBean must extend a parameterized superclass...)");
        ReflectionUtils.determineFirstGenericParam(MockReflectionUtilsNonExtendingBean.class);
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
        exception.expectMessage("Class: ch.alv.components.core.mock.MockReflectionUtilsExtendingBeanNonGeneric must extend a parameterized superclass...");
        ReflectionUtils.determineFirstGenericParam(MockReflectionUtilsExtendingBeanNonGeneric.class);
    }

    @Test
    public void testDetermineParameterClassOfParameterizedSuperClass() throws ReflectionUtilsException {
        Assert.assertEquals(String.class, ReflectionUtils.determineGenericParam(MockReflectionUtilsExtendingBean.class, 0));
        Assert.assertEquals(Long.class, ReflectionUtils.determineGenericParam(MockReflectionUtilsExtendingBean.class, 1));
        Assert.assertEquals(Integer.class, ReflectionUtils.determineGenericParam(MockReflectionUtilsExtendingBean.class, 2));
    }

    @Test
    public void testFailDetermineParameterClassOfParameterizedSuperClass() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("No Type Parameter defined for: 'ch.alv.components.core.mock.MockReflectionUtilsParameterizedSuperClass<java.lang.String, java.lang.Long, java.lang.Integer>' with index '4'");
        ReflectionUtils.determineGenericParam(MockReflectionUtilsExtendingBean.class, 4);
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
        ReflectionUtils.determineGenericParam(MockReflectionUtilsExtendingBean.class, -1);
    }

    @Test
    public void failDetermineParameterClassOfParameterizedSuperClassTarget() throws ReflectionUtilsException {
        exception.expect(ReflectionUtilsException.class);
        exception.expectMessage("Can't resolve a parameter class on type Class.class");
        ReflectionUtils.determineGenericParam(Class.class, 0);
    }

    @Test
    public void testNewInstanceQuietly() throws ReflectionUtilsException {
        ReflectionUtils.newInstanceQuietly(ReflectionUtilsTest.class);
        // Should not throw an exception, even it can't be instantiated with default constructor.
        ReflectionUtils.newInstanceQuietly(WithConstructorArg.class);
    }

    @Test
    public void fullCoverageForStaticTest() throws ReflectionUtilsException {
        new ReflectionUtils();
    }

    private class WithConstructorArg {
        public WithConstructorArg(String argument) {}
    }

}
