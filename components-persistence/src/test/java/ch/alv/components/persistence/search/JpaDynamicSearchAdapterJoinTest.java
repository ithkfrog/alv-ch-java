package ch.alv.components.persistence.search;

import ch.alv.components.persistence.search.internal.ComparatorType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO: put some comment
 *
 * @since 1.0.0
 */
public class JpaDynamicSearchAdapterJoinTest {

    private DynamicSearch search;

    @Before
    public void prepare() {
        search = new DynamicSearchImpl();
    }

    @Test
    public void testEqualJoin() {
        search.select("a.*", "b.*")
                .from("a", BeanA.class)
                .from("b", BeanB.class)
                .where("a", "key", "b.key");

        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);

        String queryString = adapter.render();
        Assert.assertEquals("SELECT a, b FROM BeanA a, BeanB b WHERE a.key = b.key", queryString);
    }

    @Test
    public void testLikeJoin() {
        search.select("a.*", "b.*")
                .from("a", BeanA.class)
                .from("b", BeanB.class)
                .where("a", "key", ComparatorType.LIKE, "b.key");

        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);

        String queryString = adapter.render();
        Assert.assertEquals("SELECT a, b FROM BeanA a, BeanB b WHERE a.key LIKE b.key", queryString);
    }


}
