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
 * Join related unit tests for the {@link JpaQueryRenderer#render(SelectQuery)} method.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class QueryRendererJoinTest {

    private static Map<String, Object> values;

    @BeforeClass
    public static void prepare() {
        values = new HashMap<String, Object>();
        values.put("aKey", "testKey");
        values.put("aValue", 6);
        values.put("aList", new ArrayList<>());
    }

    @Test
    public void testEqualJoin() {
        SelectQuery query = new SelectQuery();
        query.select("a.*", "b.*")
                .from("a", BeanA.class)
                .from("b", BeanB.class)
                .where("a", "key", "b.key");
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a, b FROM BeanA a, BeanB b WHERE a.key = b.key", queryString);
    }

    @Test
    public void testLikeJoin() {
        SelectQuery query = new SelectQuery();
        query.select("a.*", "b.*")
                .from("a", BeanA.class)
                .from("b", BeanB.class)
                .where("a", "key", QueryOperator.LIKE, "b.key");
        String queryString = JpaQueryRenderer.render(query);
        Assert.assertEquals("SELECT a, b FROM BeanA a, BeanB b WHERE a.key LIKE b.key", queryString);
    }

}
