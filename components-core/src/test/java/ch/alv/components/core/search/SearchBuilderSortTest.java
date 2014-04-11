package ch.alv.components.core.search;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @since 1.0.0
 */
public class SearchBuilderSortTest {

    private SearchBuilder searchBuilder = new SearchBuilder();

    @Before
    public void prepare() {
        searchBuilder.reset();
    }

    @Test
    public void testSingleSorting() {
        searchBuilder.sortAsc("a", "firstAttribute");
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(1, search.getSortings().size());
        SearchSorting firstItem = search.getSortings().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getAttribute());
        Assert.assertEquals(SortType.ASC, firstItem.getSortType());
    }

    @Test
    public void testDirectedSingleSorting() {
        searchBuilder.sortDesc("a", "firstAttribute");
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(1, search.getSortings().size());
        SearchSorting firstItem = search.getSortings().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getAttribute());
        Assert.assertEquals(SortType.DESC, firstItem.getSortType());
    }

    @Test
    public void testMultipleSorting() {
        searchBuilder.sortDesc("a", "firstAttribute").sortAsc("b", "secondAttribute");
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getSortings().size());

        SearchSorting firstItem = search.getSortings().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getAttribute());
        Assert.assertEquals(SortType.DESC, firstItem.getSortType());

        SearchSorting secondItem = search.getSortings().get(1);
        Assert.assertEquals("b", secondItem.getSourceToken());
        Assert.assertEquals("secondAttribute", secondItem.getAttribute());
        Assert.assertEquals(SortType.ASC, secondItem.getSortType());
    }

    @Test
    public void testDirectedMultipleSorting() {
        searchBuilder.sortDesc("a", "firstAttribute").sortDesc("b", "secondAttribute");
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getSortings().size());

        SearchSorting firstItem = search.getSortings().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getAttribute());
        Assert.assertEquals(SortType.DESC, firstItem.getSortType());

        SearchSorting secondItem = search.getSortings().get(1);
        Assert.assertEquals("b", secondItem.getSourceToken());
        Assert.assertEquals("secondAttribute", secondItem.getAttribute());
        Assert.assertEquals(SortType.DESC, secondItem.getSortType());
    }

}
