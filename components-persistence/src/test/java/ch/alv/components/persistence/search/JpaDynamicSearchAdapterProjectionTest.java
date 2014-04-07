package ch.alv.components.persistence.search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Projection-related tests of the JpaDynamicSearchAdapter
 *
 * @since 1.0.0
 */
public class JpaDynamicSearchAdapterProjectionTest {

    private DynamicSearch search;

    @Before
    public void prepare() {
        search = new DynamicSearchImpl();
    }

    @Test
    public void testNoProjectionQuery() {
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        search.select("a.*")
                .from("a", BeanA.class);
        Assert.assertEquals("SELECT a FROM BeanA a", adapter.render());
    }

    @Test
    public void testSingleProjectionQuery() {
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        search.select("a.key")
                .from("a", BeanA.class);
        Assert.assertEquals("SELECT a.key FROM BeanA a", adapter.render());
    }

    @Test
    public void testMultipleProjectionQuery() {
        search.select("a.key", "a.value")
                .from("a", BeanA.class);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a.key, a.value FROM BeanA a", adapter.render());
    }

    @Test
    public void testJoinedProjectionQuery() {
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        search.select("a.*", "b.value")
                .from("a", BeanA.class)
                .from("b", BeanB.class);
        Assert.assertEquals("SELECT a, b.value FROM BeanA a, BeanB b", adapter.render());
    }


}
