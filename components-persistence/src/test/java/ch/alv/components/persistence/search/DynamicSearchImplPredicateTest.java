package ch.alv.components.persistence.search;

import ch.alv.components.persistence.search.internal.ComparatorType;
import ch.alv.components.persistence.search.internal.ConcatType;
import ch.alv.components.persistence.search.internal.PredicateItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @since 1.0.0
 */
public class DynamicSearchImplPredicateTest {

    @Test
    public void testPredicate() {
        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute");

        Assert.assertEquals(1, search.getWheres().size());
        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(null, firstItem.getImmutableValue());
        Assert.assertEquals(ConcatType.AND, firstItem.getConcatType());
    }

    @Test
    public void testImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute", immutableValue);

        Assert.assertEquals(1, search.getWheres().size());
        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(ConcatType.AND, firstItem.getConcatType());
        Assert.assertEquals(immutableValue, firstItem.getImmutableValue());
    }

    @Test
    public void testComparatorType() {
        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute", ComparatorType.NOT_EQUALS);

        Assert.assertEquals(1, search.getWheres().size());
        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(null, firstItem.getImmutableValue());
        Assert.assertEquals(ConcatType.AND, firstItem.getConcatType());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, firstItem.getComparator());
    }

    @Test
    public void testComparatorTypeAndImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute", ComparatorType.NOT_EQUALS, immutableValue);

        Assert.assertEquals(1, search.getWheres().size());
        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, firstItem.getComparator());
        Assert.assertEquals(ConcatType.AND, firstItem.getConcatType());
        Assert.assertEquals(immutableValue, firstItem.getImmutableValue());
    }
}
