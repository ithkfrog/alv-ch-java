package ch.alv.components.persistence.search;

import org.junit.Assert;
import org.junit.Test;


/**
 * @since 1.0.0
 */
public class DynamicSearchImplBaseTest {

    private DynamicSearch search = new DynamicSearchImpl();

    @Test
    public void testSingleSelect() {
        DynamicSearch search = new DynamicSearchImpl().select("a");
        Assert.assertEquals(1, search.getSelects().length);
        Assert.assertEquals("a", search.getSelects()[0]);
    }

    @Test
    public void testMultipleSelect() {
        DynamicSearch search = new DynamicSearchImpl().select("a.firstAttribute", "a.secondAttribute");
        Assert.assertEquals(2, search.getSelects().length);
        Assert.assertEquals("a.firstAttribute", search.getSelects()[0]);
        Assert.assertEquals("a.secondAttribute", search.getSelects()[1]);
    }

    @Test
    public void testSingleFrom() {
        DynamicSearch search = new DynamicSearchImpl().from("a", BeanA.class);
        Assert.assertEquals(1, search.getFroms().size());
        Assert.assertEquals("a", search.getFroms().get(0).getEntityToken());
        Assert.assertEquals(BeanA.class, search.getFroms().get(0).getEntityClass());
    }

    @Test
    public void testMultipleFrom() {
        DynamicSearch search = new DynamicSearchImpl().from("a", BeanA.class).from("b", BeanB.class);
        Assert.assertEquals(2, search.getFroms().size());
        Assert.assertEquals("a", search.getFroms().get(0).getEntityToken());
        Assert.assertEquals(BeanA.class, search.getFroms().get(0).getEntityClass());
        Assert.assertEquals("b", search.getFroms().get(1).getEntityToken());
        Assert.assertEquals(BeanB.class, search.getFroms().get(1).getEntityClass());
    }

}
