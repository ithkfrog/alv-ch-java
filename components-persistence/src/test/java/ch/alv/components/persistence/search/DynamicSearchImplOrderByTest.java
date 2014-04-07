package ch.alv.components.persistence.search;

import ch.alv.components.persistence.search.internal.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @since 1.0.0
 */
public class DynamicSearchImplOrderByTest {

    @Test
    public void testSingleSorting() {
        DynamicSearch search = new DynamicSearchImpl().orderBy(new Sorting("a", "firstAttribute"));

        Assert.assertEquals(1, search.getSortings().size());

        Assert.assertTrue(search.getSortings().get(0) instanceof Sorting);
        Sorting firstItem = (Sorting) search.getSortings().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(SortType.ASC, firstItem.getType());
    }

    @Test
    public void testDirectedSingleSorting() {
        DynamicSearch search = new DynamicSearchImpl().orderBy(new Sorting("a", "firstAttribute", SortType.DESC));

        Assert.assertEquals(1, search.getSortings().size());

        Assert.assertTrue(search.getSortings().get(0) instanceof Sorting);
        Sorting firstItem = (Sorting) search.getSortings().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(SortType.DESC, firstItem.getType());
    }

    @Test
    public void testMultipleSorting() {
        DynamicSearch search = new DynamicSearchImpl().orderBy(new Sorting("a", "firstAttribute")).orderBy(new Sorting("b", "secondAttribute"));

        Assert.assertEquals(2, search.getSortings().size());

        Assert.assertTrue(search.getSortings().get(0) instanceof Sorting);
        Sorting firstItem = (Sorting) search.getSortings().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(SortType.ASC, firstItem.getType());

        Assert.assertTrue(search.getSortings().get(1) instanceof Sorting);
        Sorting secondItem = (Sorting) search.getSortings().get(1);
        Assert.assertEquals("b", secondItem.getEntityToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(SortType.ASC, secondItem.getType());
    }

    @Test
    public void testDirectedMultipleSorting() {
        DynamicSearch search = new DynamicSearchImpl().orderBy(new Sorting("a", "firstAttribute", SortType.DESC)).orderBy(new Sorting("b", "secondAttribute", SortType.DESC));

        Assert.assertEquals(2, search.getSortings().size());

        Assert.assertTrue(search.getSortings().get(0) instanceof Sorting);
        Sorting firstItem = (Sorting) search.getSortings().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(SortType.DESC, firstItem.getType());

        Assert.assertTrue(search.getSortings().get(1) instanceof Sorting);
        Sorting secondItem = (Sorting) search.getSortings().get(1);
        Assert.assertEquals("b", secondItem.getEntityToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(SortType.DESC, secondItem.getType());
    }

}
