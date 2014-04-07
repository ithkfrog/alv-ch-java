package ch.alv.components.persistence.search;

import ch.alv.components.persistence.search.internal.ComparatorType;
import ch.alv.components.persistence.testcommons.TestValuesProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Base tests for the JpaDynamicSearchAdapter
 *
 * @since 1.0.0
 */
public class JpaDynamicSearchAdapterTest {

    private DynamicSearch search;

    @Before
    public void prepare() {
        search = new DynamicSearchImpl();
    }

    @Test
    public void testEquals() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key");
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key = :aKey", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testNotEquals() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", ComparatorType.NOT_EQUALS);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value != :aValue", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testLike() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key", ComparatorType.LIKE);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key LIKE :aKey", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testNotLike() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key", ComparatorType.NOT_LIKE);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key NOT LIKE :aKey", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testGreaterThan() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", ComparatorType.GT);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value > :aValue", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testGreaterEqualThan() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", ComparatorType.GE);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value >= :aValue", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testLessThan() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", ComparatorType.LT);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value < :aValue", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testLessEqualThan() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", ComparatorType.LE);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value <= :aValue", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testIn() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "list", ComparatorType.IN);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.list IN :aList", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testNotIn() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "list", ComparatorType.NOT_IN);
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.list NOT IN :aList", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testAND() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key", ComparatorType.LIKE)
                .and("a", "value");
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key LIKE :aKey AND a.value = :aValue", adapter.render(new TestValuesProvider()));
    }

    @Test
    public void testOR() {
        search.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key", ComparatorType.LT)
                .or("a", "value");
        JpaDynamicSearchAdapter adapter = new JpaDynamicSearchAdapter(search);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key < :aKey OR a.value = :aValue", adapter.render(new TestValuesProvider()));
    }
}
