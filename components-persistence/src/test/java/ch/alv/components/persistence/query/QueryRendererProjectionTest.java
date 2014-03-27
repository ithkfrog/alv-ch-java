package ch.alv.components.persistence.query;

import ch.alv.components.persistence.query.renderer.JpaQueryRenderer;
import junit.framework.Assert;
import org.junit.Test;


/**
 * Projection related unit tests for the {@link JpaQueryRenderer#render(SelectQuery)} method.
 *
 * @author seco-hrf
 * @since 1.0.0
 */
public class QueryRendererProjectionTest {

    @Test
    public void testNoProjectionQuery() {
        SelectQuery query = new SelectQuery();
        query.select("a.*")
                .from("a", BeanA.class);
        Assert.assertEquals("SELECT a FROM BeanA a", JpaQueryRenderer.render(query));
    }

    @Test
    public void testSingleProjectionQuery() {
        SelectQuery query = new SelectQuery();
        query.select("a.key")
                .from("a", BeanA.class);
        Assert.assertEquals("SELECT a.key FROM BeanA a", JpaQueryRenderer.render(query));
    }

    @Test
    public void testMultipleProjectionQuery() {
        SelectQuery query = new SelectQuery();
        query.select("a.key", "a.value")
                .from("a", BeanA.class);
        Assert.assertEquals("SELECT a.key, a.value FROM BeanA a", JpaQueryRenderer.render(query));
    }

    @Test
    public void testJoinedProjectionQuery() {
        SelectQuery query = new SelectQuery();
        query.select("a.*", "b.value")
                .from("a", BeanA.class)
                .from("b", BeanB.class);
        Assert.assertEquals("SELECT a, b.value FROM BeanA a, BeanB b", JpaQueryRenderer.render(query));
    }

}
