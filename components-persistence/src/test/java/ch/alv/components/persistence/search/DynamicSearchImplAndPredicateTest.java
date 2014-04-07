package ch.alv.components.persistence.search;

import ch.alv.components.persistence.search.internal.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @since 1.0.0
 */
public class DynamicSearchImplAndPredicateTest {

    @Test
    public void testPredicate() {
        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute").and("b", "secondAttribute");

        Assert.assertEquals(2, search.getWheres().size());
        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(null, firstItem.getImmutableValue());

        Assert.assertTrue(search.getWheres().get(1) instanceof PredicateItem);
        PredicateItem secondItem = (PredicateItem) search.getWheres().get(1);
        Assert.assertEquals("b", secondItem.getEntityToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparator());
        Assert.assertEquals(null, secondItem.getImmutableValue());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
    }

    @Test
    public void testImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute").and("b", "secondAttribute", immutableValue);

        Assert.assertEquals(2, search.getWheres().size());
        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(null, firstItem.getImmutableValue());

        Assert.assertTrue(search.getWheres().get(1) instanceof PredicateItem);
        PredicateItem secondItem = (PredicateItem) search.getWheres().get(1);
        Assert.assertEquals("b", secondItem.getEntityToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparator());
        Assert.assertEquals(immutableValue, secondItem.getImmutableValue());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
    }

    @Test
    public void testComparatorType() {
        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute").and("b", "secondAttribute", ComparatorType.NOT_EQUALS);

        Assert.assertEquals(2, search.getWheres().size());
        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(null, firstItem.getImmutableValue());

        Assert.assertTrue(search.getWheres().get(1) instanceof PredicateItem);
        PredicateItem secondItem = (PredicateItem) search.getWheres().get(1);
        Assert.assertEquals("b", secondItem.getEntityToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, secondItem.getComparator());
        Assert.assertEquals(null, secondItem.getImmutableValue());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
    }

    @Test
    public void testComparatorTypeAndImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute").and("b", "secondAttribute", ComparatorType.NOT_EQUALS, immutableValue);

        Assert.assertEquals(2, search.getWheres().size());
        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(null, firstItem.getImmutableValue());
        
        Assert.assertTrue(search.getWheres().get(1) instanceof PredicateItem);
        PredicateItem secondItem = (PredicateItem) search.getWheres().get(1);
        Assert.assertEquals("b", secondItem.getEntityToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, secondItem.getComparator());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
        Assert.assertEquals(immutableValue, secondItem.getImmutableValue());
    }

    @Test
    public void testPredicateList() {

        List<Predicate> list = new ArrayList<>();
        list.add(new PredicateItem("b", "secondAttribute"));
        list.add(new PredicateItem("c", "thirdAttribute"));

        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute").and(list);

        Assert.assertEquals(2, search.getWheres().size());

        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(null, firstItem.getImmutableValue());

        Assert.assertTrue(search.getWheres().get(1) instanceof PredicateGroup);
        PredicateGroup group = (PredicateGroup) search.getWheres().get(1);

        Assert.assertEquals(ConcatType.AND, group.getConcatType());

        Assert.assertEquals(2, group.getPredicates().size());

        Assert.assertTrue(group.getPredicates().get(0) instanceof PredicateItem);
        PredicateItem secondItem = (PredicateItem) group.getPredicates().get(0);
        Assert.assertEquals("b", secondItem.getEntityToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparator());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
        Assert.assertEquals(null, secondItem.getImmutableValue());

        Assert.assertTrue(group.getPredicates().get(1) instanceof PredicateItem);
        PredicateItem thirdItem = (PredicateItem) group.getPredicates().get(1);
        Assert.assertEquals("c", thirdItem.getEntityToken());
        Assert.assertEquals("thirdAttribute", thirdItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, thirdItem.getComparator());
        Assert.assertEquals(ConcatType.AND, thirdItem.getConcatType());
        Assert.assertEquals(null, thirdItem.getImmutableValue());
    }

    @Test
    public void testPredicateGroup() {

        PredicateGroup group = new PredicateGroup();
        group.and("b", "secondAttribute");
        group.or("c", "thirdAttribute");

        DynamicSearch search = new DynamicSearchImpl().where("a", "firstAttribute").and(group);

        Assert.assertEquals(2, search.getWheres().size());

        Assert.assertTrue(search.getWheres().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) search.getWheres().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(null, firstItem.getImmutableValue());

        Assert.assertTrue(search.getWheres().get(1) instanceof PredicateGroup);
        PredicateGroup localGroup = (PredicateGroup) search.getWheres().get(1);

        Assert.assertEquals(ConcatType.AND, localGroup.getConcatType());

        Assert.assertEquals(2, localGroup.getPredicates().size());

        Assert.assertTrue(localGroup.getPredicates().get(0) instanceof PredicateItem);
        PredicateItem secondItem = (PredicateItem) localGroup.getPredicates().get(0);
        Assert.assertEquals("b", secondItem.getEntityToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparator());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
        Assert.assertEquals(null, secondItem.getImmutableValue());

        Assert.assertTrue(localGroup.getPredicates().get(1) instanceof PredicateItem);
        PredicateItem thirdItem = (PredicateItem) localGroup.getPredicates().get(1);
        Assert.assertEquals("c", thirdItem.getEntityToken());
        Assert.assertEquals("thirdAttribute", thirdItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, thirdItem.getComparator());
        Assert.assertEquals(ConcatType.OR, thirdItem.getConcatType());
        Assert.assertEquals(null, thirdItem.getImmutableValue());
    }
}
