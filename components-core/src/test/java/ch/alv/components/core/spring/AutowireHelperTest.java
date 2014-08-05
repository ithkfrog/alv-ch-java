package ch.alv.components.core.spring;

import ch.alv.components.core.mock.MockBeanA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the AutowireHelper.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/autowire-helper-test-context.xml")
public class AutowireHelperTest {

    @Resource
    private MockBeanA member;

    @Test
    public void testAutowire() {
        TestClass testClass = new TestClass();
        assertNull(testClass.getMember());
        testClass.autowire();
        assertEquals(member, testClass.getMember());

        testClass = new TestClass();
        MockBeanA a = new MockBeanA();
        testClass.setMember(a);
        testClass.autowire();
        assertEquals(a, testClass.getMember());
    }

    public class TestClass {

        @Resource
        private MockBeanA member;

        public MockBeanA getMember() {
            return member;
        }

        public void setMember(MockBeanA member) {
            this.member = member;
        }

        public void autowire() {
            AutowireHelper.autowire(this, this.member);
        }
    }

}
