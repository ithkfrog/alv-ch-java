package ch.alv.components.persistence.query;

import ch.alv.components.persistence.query.internal.QueryOperator;
import ch.alv.components.persistence.query.renderer.JpaQueryRenderer;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Selection related unit tests for the {@link JpaQueryRenderer#render(SelectQuery)} method.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class QueryRendererSelectionTest {

    private static Map<String, Object> values;

    @BeforeClass
    public static void prepare() {
        values = new HashMap<String, Object>();
        values.put("aKey", "testKey");
        values.put("aValue", 6);
        values.put("aList", new ArrayList<>());
    }

    @Test
    public void testEquals() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key");
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key = :aKey", queryString);
    }

    @Test
    public void testNotEquals() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", QueryOperator.NOT_EQUALS);
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value != :aValue", queryString);
    }

    @Test
    public void testLike() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key", QueryOperator.LIKE);
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key LIKE :aKey", queryString);
    }

    @Test
    public void testNotLike() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key", QueryOperator.NOT_LIKE);
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key NOT LIKE :aKey", queryString);
    }

    @Test
    public void testGreaterThan() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", QueryOperator.GT);
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value > :aValue", queryString);
    }

    @Test
    public void testGreaterEqualThan() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", QueryOperator.GE);
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value >= :aValue", queryString);
    }

    @Test
    public void testLessThan() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", QueryOperator.LT);
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value < :aValue", queryString);
    }

    @Test
    public void testLessEqualThan() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "value", QueryOperator.LE);
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.value <= :aValue", queryString);
    }

    @Test
    public void testIn() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "list", QueryOperator.IN);
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.list IN :aList", queryString);
    }

    @Test
    public void testNotIn() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "list", QueryOperator.NOT_IN);
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.list NOT IN :aList", queryString);
    }

    @Test
    public void testAND() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key", QueryOperator.LIKE)
                .and("a", "value");
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key LIKE :aKey AND a.value = :aValue", queryString);
    }

    @Test
    public void testOR() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class)
                .where("a", "key", QueryOperator.LT)
                .or("a", "value");
        query.setValues(values);
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a FROM BeanA a WHERE a.key < :aKey OR a.value = :aValue", queryString);
    }

}
