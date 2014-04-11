package ch.alv.components.core.search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0
 */
public class GroupPredicateTest {

    private SearchBuilder searchBuilder = new SearchBuilder();

    @Before
    public void prepare() {
        searchBuilder.reset();
    }

    @Test
    public void testSinglePredicate() {

        GroupPredicate group = new GroupPredicate();
        group.and("a", "firstAttribute");

        Assert.assertEquals(ConcatType.AND, group.getConcatType());
        Assert.assertEquals(1, group.getPredicates().size());

        Assert.assertEquals(SinglePredicate.class, group.getPredicates().get(0).getClass());
        SinglePredicate item = (SinglePredicate) group.getPredicates().get(0);
        Assert.assertEquals(ConcatType.AND, item.getConcatType());
        Assert.assertEquals("a", item.getSourceToken());
        Assert.assertEquals("firstAttribute", item.getName());
        Assert.assertEquals(null, item.getValue());
        Assert.assertEquals(ComparatorType.EQUALS, item.getComparatorType());
    }

    @Test
    public void testAndMultiPredicate() {
        GroupPredicate group = new GroupPredicate();
        group.and("a", "firstAttribute");
        group.and("b", "secondAttribute");

        Assert.assertEquals(2, group.getPredicates().size());
        Assert.assertTrue(group.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) group.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(null, firstItem.getValue());

        Assert.assertTrue(group.getPredicates().get(1) instanceof SinglePredicate);
        SinglePredicate secondItem = (SinglePredicate) group.getPredicates().get(1);
        Assert.assertEquals("b", secondItem.getSourceToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparatorType());
        Assert.assertEquals(null, secondItem.getValue());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
    }

    @Test
    public void testImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        GroupPredicate group = new GroupPredicate();
        group.and("a", "firstAttribute", immutableValue);

        Assert.assertEquals(1, group.getPredicates().size());
        Assert.assertTrue(group.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) group.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(immutableValue, firstItem.getValue());
    }

    @Test
    public void testComparatorType() {
        GroupPredicate group = new GroupPredicate();
        group.and("a", "firstAttribute", ComparatorType.NOT_EQUALS);

        Assert.assertEquals(1, group.getPredicates().size());
        Assert.assertTrue(group.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) group.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(null, firstItem.getValue());
    }

    @Test
    public void testComparatorTypeAndImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        GroupPredicate group = new GroupPredicate();
        group.and("a", "firstAttribute", immutableValue, ComparatorType.NOT_EQUALS);

        Assert.assertEquals(1, group.getPredicates().size());
        Assert.assertTrue(group.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) group.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(immutableValue, firstItem.getValue());

    }

    @Test
    public void testPredicateList() {
        List<Predicate> list = new ArrayList<>();
        list.add(new SinglePredicate("a", "firstAttribute"));
        list.add(new SinglePredicate("b", "secondAttribute"));

        GroupPredicate group = new GroupPredicate();
        group.and(list);

        Assert.assertEquals(1, group.getPredicates().size());
        Assert.assertTrue(group.getPredicates().get(0) instanceof GroupPredicate);
    }

    @Test
    public void testPredicateGroup() {

        GroupPredicate testGroup = new GroupPredicate();
        testGroup.and("a", "firstAttribute");

        GroupPredicate group = new GroupPredicate();
        group.and(testGroup);

        Assert.assertEquals(1, group.getPredicates().size());

        Assert.assertTrue(group.getPredicates().get(0) instanceof GroupPredicate);
        GroupPredicate firstItem = (GroupPredicate) group.getPredicates().get(0);

        Assert.assertEquals(1, firstItem.getPredicates().size());
        Assert.assertTrue(((GroupPredicate) group.getPredicates().get(0)).getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate subItem = (SinglePredicate) ((GroupPredicate) group.getPredicates().get(0)).getPredicates().get(0);
        Assert.assertEquals("a", subItem.getSourceToken());
        Assert.assertEquals("firstAttribute", subItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, subItem.getComparatorType());
        Assert.assertEquals(null, subItem.getValue());
    }
}
