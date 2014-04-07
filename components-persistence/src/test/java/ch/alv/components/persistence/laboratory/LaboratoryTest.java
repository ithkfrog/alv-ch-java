package ch.alv.components.persistence.laboratory;

import ch.alv.components.persistence.search.BeanA;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

/**
 * TODO: put some comment
 *
 * @since 1.0.0
 */
public class LaboratoryTest {

    @Test
    public void test() {
        BeanA a = new BeanA();
        a.setValue("bla");
        a.setKey("keyBla");

        BeanA b = new BeanA();
        b.setValue("bla");
        b.setKey("keyBla");
        System.out.println(EqualsBuilder.reflectionEquals(a, b));
    }

}
