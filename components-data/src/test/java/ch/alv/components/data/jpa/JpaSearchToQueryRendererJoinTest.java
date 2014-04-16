package ch.alv.components.data.jpa;

import ch.alv.components.core.search.ComparatorType;
import ch.alv.components.core.search.EmptyValuesProvider;
import ch.alv.components.core.search.SearchBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Join-related tests of the JpaDynamicSearchAdapter
 *
 * @since 1.0.0
 */
public class JpaSearchToQueryRendererJoinTest {

    private SearchBuilder searchBuilder = new SearchBuilder();

    @Before
    public void prepare() {
        searchBuilder.reset();
    }

    @Test
    public void testEqualJoin() {
        searchBuilder.find("a", "*")
                .find("b", "*")
                .in("a", "BeanA")
                .in("b", "BeanB")
                .where("a", "key", "b.key");

        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);

        String queryString = (String) renderer.render(searchBuilder.build(), new EmptyValuesProvider());

        Assert.assertEquals("SELECT a, b FROM BeanA a, BeanB b WHERE a.key = b.key", queryString);
    }

    @Test
    public void testLikeJoin() {
        searchBuilder.find("a", "*")
                .find("b", "*")
                .in("a", "BeanA")
                .in("b", "BeanB")
                .where("a", "key", "b.key", ComparatorType.LIKE);
        JpaSearchToQueryRenderer renderer = new JpaSearchToQueryRenderer(null);
        String queryString = (String) renderer.render(searchBuilder.build(), new EmptyValuesProvider());

        Assert.assertEquals("SELECT a, b FROM BeanA a, BeanB b WHERE a.key LIKE b.key", queryString);
    }


}
