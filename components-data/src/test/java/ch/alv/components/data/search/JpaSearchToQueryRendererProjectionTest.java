package ch.alv.components.data.search;

import ch.alv.components.core.search.SearchBuilder;
import ch.alv.components.data.jpa.JpaSearchToQueryRenderer;
import ch.alv.components.data.testcommons.TestValuesProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Projection-related tests of the JpaDynamicSearchAdapter
 *
 * @since 1.0.0
 */
public class JpaSearchToQueryRendererProjectionTest {

    private SearchBuilder searchBuilder = new SearchBuilder();

    @Before
    public void prepare() {
        searchBuilder.reset();
    }

    @Test
    public void testNoProjectionQuery() {
        searchBuilder.find("a", "*").in("a", "BeanA");
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a", queryString);
    }

    @Test
    public void testSingleProjectionQuery() {
        searchBuilder.find("a", "key").in("a", "BeanA");
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a.key FROM BeanA a", queryString);
    }

    @Test
    public void testMultipleProjectionQuery() {
        searchBuilder.find("a", "key").find("a", "value").in("a", "BeanA");
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a.key, a.value FROM BeanA a", queryString);
    }

    @Test
    public void testJoinedProjectionQuery() {
        searchBuilder.find("a", "*").find("b", "value").in("a", "BeanA").in("b", "BeanB");
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a, b.value FROM BeanA a, BeanB b", queryString);
    }

}
