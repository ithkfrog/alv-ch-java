package ch.alv.components.core.search;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @since 1.0.0
 */
public class SearchBuilderBaseTest {

    private SearchBuilder searchBuilder = new SearchBuilder();

    @Before
    public void prepare() {
        searchBuilder.reset();
    }


    @Test
    public void testSingleSelect() {
        searchBuilder.find("a", "*");
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(1, search.getProjections().size());
        Assert.assertEquals("a", search.getProjections().get(0).getSourceToken());
        Assert.assertEquals("*", search.getProjections().get(0).getAttributeName());
    }

    @Test
    public void testMultipleSelect() {
        searchBuilder.find("a", "firstAttribute").find("a", "secondAttribute");
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getProjections().size());
        Assert.assertEquals("a", search.getProjections().get(0).getSourceToken());
        Assert.assertEquals("firstAttribute", search.getProjections().get(0).getAttributeName());
        Assert.assertEquals("a", search.getProjections().get(1).getSourceToken());
        Assert.assertEquals("secondAttribute", search.getProjections().get(1).getAttributeName());
    }

    @Test
    public void testSingleFrom() {
        searchBuilder.in("a", "BeanA");
        SearchImpl search = searchBuilder.build();
        Assert.assertEquals(1, search.getSources().size());
        Assert.assertEquals("a", search.getSources().get(0).getToken());
        Assert.assertEquals("BeanA", search.getSources().get(0).getName());
    }

    @Test
    public void testMultipleFrom() {
        searchBuilder.in("a", "BeanA").in("b", "BeanB");
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getSources().size());
        Assert.assertEquals("a", search.getSources().get(0).getToken());
        Assert.assertEquals("BeanA", search.getSources().get(0).getName());
        Assert.assertEquals("b", search.getSources().get(1).getToken());
        Assert.assertEquals("BeanB", search.getSources().get(1).getName());
    }

}
