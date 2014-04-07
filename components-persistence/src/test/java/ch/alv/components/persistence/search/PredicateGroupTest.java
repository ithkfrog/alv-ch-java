package ch.alv.components.persistence.search;

import ch.alv.components.persistence.search.internal.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0
 */
public class PredicateGroupTest {

    @Test
    public void testSinglePredicate() {

        PredicateGroup group = new PredicateGroup();
        group.and("a", "firstAttribute");

        Assert.assertEquals(ConcatType.AND, group.getConcatType());
        Assert.assertEquals(1, group.getPredicates().size());

        Assert.assertEquals(PredicateItem.class, group.getPredicates().get(0).getClass());
        PredicateItem item = (PredicateItem) group.getPredicates().get(0);
        Assert.assertEquals(ConcatType.AND, item.getConcatType());
        Assert.assertEquals("a", item.getEntityToken());
        Assert.assertEquals("firstAttribute", item.getName());
        Assert.assertEquals(null, item.getImmutableValue());
        Assert.assertEquals(ComparatorType.EQUALS, item.getComparator());
    }

    @Test
    public void testAndMultiPredicate() {
        PredicateGroup group = new PredicateGroup();
        group.and("a", "firstAttribute");
        group.and("b", "secondAttribute");

        Assert.assertEquals(2, group.getPredicates().size());
        Assert.assertTrue(group.getPredicates().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) group.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(null, firstItem.getImmutableValue());

        Assert.assertTrue(group.getPredicates().get(1) instanceof PredicateItem);
        PredicateItem secondItem = (PredicateItem) group.getPredicates().get(1);
        Assert.assertEquals("b", secondItem.getEntityToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparator());
        Assert.assertEquals(null, secondItem.getImmutableValue());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
    }

    @Test
    public void testImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        PredicateGroup group = new PredicateGroup();
        group.and("a", "firstAttribute", immutableValue);

        Assert.assertEquals(1, group.getPredicates().size());
        Assert.assertTrue(group.getPredicates().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) group.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparator());
        Assert.assertEquals(immutableValue, firstItem.getImmutableValue());
    }

    @Test
    public void testComparatorType() {
        PredicateGroup group = new PredicateGroup();
        group.and("a", "firstAttribute", ComparatorType.NOT_EQUALS);

        Assert.assertEquals(1, group.getPredicates().size());
        Assert.assertTrue(group.getPredicates().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) group.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, firstItem.getComparator());
        Assert.assertEquals(null, firstItem.getImmutableValue());

    }

    @Test
    public void testComparatorTypeAndImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        PredicateGroup group = new PredicateGroup();
        group.and("a", "firstAttribute", ComparatorType.NOT_EQUALS, immutableValue);

        Assert.assertEquals(1, group.getPredicates().size());
        Assert.assertTrue(group.getPredicates().get(0) instanceof PredicateItem);
        PredicateItem firstItem = (PredicateItem) group.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getEntityToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, firstItem.getComparator());
        Assert.assertEquals(immutableValue, firstItem.getImmutableValue());

    }

    @Test
    public void testPredicateList() {

        List<Predicate> list = new ArrayList<>();
        list.add(new PredicateItem("a", "firstAttribute"));
        list.add(new PredicateItem("b", "secondAttribute"));

        PredicateGroup group = new PredicateGroup();
        group.and(list);

        Assert.assertEquals(1, group.getPredicates().size());

        Assert.assertTrue(group.getPredicates().get(0) instanceof PredicateGroup);
        PredicateGroup firstItem = (PredicateGroup) group.getPredicates().get(0);
    }

    @Test
    public void testPredicateGroup() {

        PredicateGroup testGroup = new PredicateGroup();
        testGroup.and("a", "firstAttribute");

        PredicateGroup group = new PredicateGroup();
        group.and(testGroup);

        Assert.assertEquals(1, group.getPredicates().size());

        Assert.assertTrue(group.getPredicates().get(0) instanceof PredicateGroup);
        PredicateGroup firstItem = (PredicateGroup) group.getPredicates().get(0);

        Assert.assertEquals(1, firstItem.getPredicates().size());
        Assert.assertTrue(((PredicateGroup) group.getPredicates().get(0)).getPredicates().get(0) instanceof PredicateItem);
        PredicateItem subItem = (PredicateItem) ((PredicateGroup) group.getPredicates().get(0)).getPredicates().get(0);
        Assert.assertEquals("a", subItem.getEntityToken());
        Assert.assertEquals("firstAttribute", subItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, subItem.getComparator());
        Assert.assertEquals(null, subItem.getImmutableValue());
    }
}
