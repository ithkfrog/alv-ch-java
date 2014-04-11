package ch.alv.components.data.search;

import ch.alv.components.core.search.ComparatorType;
import ch.alv.components.core.search.SearchBuilder;
import ch.alv.components.data.jpa.JpaSearchToQueryRenderer;
import ch.alv.components.data.testcommons.TestValuesProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Base tests for the JpaDynamicSearchAdapter
 *
 * @since 1.0.0
 */
public class JpaSearchToQueryRendererBaseTest {

    private SearchBuilder searchBuilder = new SearchBuilder();

    @Before
    public void prepare() {
        searchBuilder.reset();
    }

    @Test
    public void testEquals() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "key");
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key = :aKey", queryString);
    }

    @Test
    public void testNotEquals() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "value", ComparatorType.NOT_EQUALS);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value != :aValue", queryString);
    }

    @Test
    public void testLike() {

        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "key", ComparatorType.LIKE);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key LIKE :aKey", queryString);
    }

    @Test
    public void testNotLike() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "key", ComparatorType.NOT_LIKE);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key NOT LIKE :aKey", queryString);
    }

    @Test
    public void testGreaterThan() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "value", ComparatorType.GT);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value > :aValue", queryString);
    }

    @Test
    public void testGreaterEqualThan() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "value", ComparatorType.GE);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value >= :aValue", queryString);
    }

    @Test
    public void testLessThan() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "key", ComparatorType.LT);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key < :aKey", queryString);
    }

    @Test
    public void testLessEqualThan() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "value", ComparatorType.LE);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value <= :aValue", queryString);
    }

    @Test
    public void testIn() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "list", ComparatorType.IN);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.list IN :aList", queryString);
    }

    @Test
    public void testNotIn() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "list", ComparatorType.NOT_IN);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.list NOT IN :aList", queryString);
    }

    @Test
    public void testAND() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "key", ComparatorType.LIKE)
                .and("a", "value");
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key LIKE :aKey AND a.value = :aValue", queryString);
    }

    @Test
    public void testOR() {
        searchBuilder.find("a", "*")
                .in("a", "BeanA")
                .where("a", "key", ComparatorType.LT)
                .or("a", "value");
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = renderer.render(searchBuilder.build(), new TestValuesProvider());
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key < :aKey OR a.value = :aValue", queryString);
    }
}
