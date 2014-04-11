package ch.alv.components.core.search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @since 1.0.0
 */
public class SinglePredicateTest {

    private SearchBuilder searchBuilder = new SearchBuilder();

    @Before
    public void prepare() {
        searchBuilder.reset();
    }

    @Test
    public void testBasePredicate() {
        searchBuilder.find("a", "*").in("a", "BeanA").where("a", "firstAttribute");
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(1, search.getPredicates().size());
        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(null, firstItem.getValue());
        Assert.assertEquals(ConcatType.AND, firstItem.getConcatType());
    }

    @Test
    public void testImmutablePredicateValue() {
        String immutableValue = "testValue";
        searchBuilder.find("a", "*").in("a", "BeanA").where("a", "firstAttribute", immutableValue);
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(1, search.getPredicates().size());
        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(ConcatType.AND, firstItem.getConcatType());
        Assert.assertEquals(immutableValue, firstItem.getValue());
    }

    @Test
    public void testComparatorType() {
        searchBuilder.find("a", "*").in("a", "BeanA").where("a", "firstAttribute", ComparatorType.NOT_EQUALS);
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(1, search.getPredicates().size());
        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(null, firstItem.getValue());
        Assert.assertEquals(ConcatType.AND, firstItem.getConcatType());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, firstItem.getComparatorType());
    }

    @Test
    public void testComparatorTypeAndImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        searchBuilder.find("a", "*").in("a", "BeanA").where("a", "firstAttribute", immutableValue, ComparatorType.NOT_EQUALS);
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(1, search.getPredicates().size());
        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(ConcatType.AND, firstItem.getConcatType());
        Assert.assertEquals(immutableValue, firstItem.getValue());
    }
}
