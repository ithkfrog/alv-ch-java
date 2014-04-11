package ch.alv.components.core.search;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0
 */
public class SearchBuilderOrTest {

    private SearchBuilder searchBuilder = new SearchBuilder();

    @Before
    public void prepare() {
        searchBuilder.reset();
    }

    @Test
    public void testPredicate() {
        searchBuilder.where("a", "firstAttribute").or("b", "secondAttribute");
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getPredicates().size());
        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(null, firstItem.getValue());

        Assert.assertTrue(search.getPredicates().get(1) instanceof SinglePredicate);
        SinglePredicate secondItem = (SinglePredicate) search.getPredicates().get(1);
        Assert.assertEquals("b", secondItem.getSourceToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparatorType());
        Assert.assertEquals(null, secondItem.getValue());
        Assert.assertEquals(ConcatType.OR, secondItem.getConcatType());
    }

    @Test
    public void testImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        searchBuilder.where("a", "firstAttribute").or("b", "secondAttribute", immutableValue);
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getPredicates().size());
        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(null, firstItem.getValue());

        Assert.assertTrue(search.getPredicates().get(1) instanceof SinglePredicate);
        SinglePredicate secondItem = (SinglePredicate) search.getPredicates().get(1);
        Assert.assertEquals("b", secondItem.getSourceToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparatorType());
        Assert.assertEquals(immutableValue, secondItem.getValue());
        Assert.assertEquals(ConcatType.OR, secondItem.getConcatType());
    }

    @Test
    public void testComparatorType() {
        searchBuilder.where("a", "firstAttribute").or("b", "secondAttribute", ComparatorType.NOT_EQUALS);
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getPredicates().size());
        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(null, firstItem.getValue());

        Assert.assertTrue(search.getPredicates().get(1) instanceof SinglePredicate);
        SinglePredicate secondItem = (SinglePredicate) search.getPredicates().get(1);
        Assert.assertEquals("b", secondItem.getSourceToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, secondItem.getComparatorType());
        Assert.assertEquals(null, secondItem.getValue());
        Assert.assertEquals(ConcatType.OR, secondItem.getConcatType());
    }

    @Test
    public void testComparatorTypeAndImmutablePredicateValue() {
        String immutableValue = "testImmutableValue";
        searchBuilder.where("a", "firstAttribute").or("b", "secondAttribute", immutableValue, ComparatorType.NOT_EQUALS);
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getPredicates().size());
        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(null, firstItem.getValue());
        
        Assert.assertTrue(search.getPredicates().get(1) instanceof SinglePredicate);
        SinglePredicate secondItem = (SinglePredicate) search.getPredicates().get(1);
        Assert.assertEquals("b", secondItem.getSourceToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.NOT_EQUALS, secondItem.getComparatorType());
        Assert.assertEquals(ConcatType.OR, secondItem.getConcatType());
        Assert.assertEquals(immutableValue, secondItem.getValue());
    }

    @Test
    public void testPredicateList() {

        List<Predicate> list = new ArrayList<>();
        list.add(new SinglePredicate("b", "secondAttribute"));
        list.add(new SinglePredicate("c", "thirdAttribute"));

        searchBuilder.where("a", "firstAttribute").or(list);
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getPredicates().size());

        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(null, firstItem.getValue());

        Assert.assertTrue(search.getPredicates().get(1) instanceof GroupPredicate);
        GroupPredicate group = (GroupPredicate) search.getPredicates().get(1);

        Assert.assertEquals(ConcatType.OR, group.getConcatType());

        Assert.assertEquals(2, group.getPredicates().size());

        Assert.assertTrue(group.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate secondItem = (SinglePredicate) group.getPredicates().get(0);
        Assert.assertEquals("b", secondItem.getSourceToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparatorType());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
        Assert.assertEquals(null, secondItem.getValue());

        Assert.assertTrue(group.getPredicates().get(1) instanceof SinglePredicate);
        SinglePredicate thirdItem = (SinglePredicate) group.getPredicates().get(1);
        Assert.assertEquals("c", thirdItem.getSourceToken());
        Assert.assertEquals("thirdAttribute", thirdItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, thirdItem.getComparatorType());
        Assert.assertEquals(ConcatType.AND, thirdItem.getConcatType());
        Assert.assertEquals(null, thirdItem.getValue());
    }

    @Test
    public void testPredicateGroup() {

        GroupPredicate group = new GroupPredicate();
        group.and("b", "secondAttribute");
        group.or("c", "thirdAttribute");
        searchBuilder.where("a", "firstAttribute").and(group);
        SearchImpl search = searchBuilder.build();

        Assert.assertEquals(2, search.getPredicates().size());

        Assert.assertTrue(search.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate firstItem = (SinglePredicate) search.getPredicates().get(0);
        Assert.assertEquals("a", firstItem.getSourceToken());
        Assert.assertEquals("firstAttribute", firstItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, firstItem.getComparatorType());
        Assert.assertEquals(null, firstItem.getValue());

        Assert.assertTrue(search.getPredicates().get(1) instanceof GroupPredicate);
        GroupPredicate localGroup = (GroupPredicate) search.getPredicates().get(1);

        Assert.assertEquals(ConcatType.AND, localGroup.getConcatType());

        Assert.assertEquals(2, localGroup.getPredicates().size());

        Assert.assertTrue(localGroup.getPredicates().get(0) instanceof SinglePredicate);
        SinglePredicate secondItem = (SinglePredicate) localGroup.getPredicates().get(0);
        Assert.assertEquals("b", secondItem.getSourceToken());
        Assert.assertEquals("secondAttribute", secondItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, secondItem.getComparatorType());
        Assert.assertEquals(ConcatType.AND, secondItem.getConcatType());
        Assert.assertEquals(null, secondItem.getValue());

        Assert.assertTrue(localGroup.getPredicates().get(1) instanceof SinglePredicate);
        SinglePredicate thirdItem = (SinglePredicate) localGroup.getPredicates().get(1);
        Assert.assertEquals("c", thirdItem.getSourceToken());
        Assert.assertEquals("thirdAttribute", thirdItem.getName());
        Assert.assertEquals(ComparatorType.EQUALS, thirdItem.getComparatorType());
        Assert.assertEquals(ConcatType.OR, thirdItem.getConcatType());
        Assert.assertEquals(null, thirdItem.getValue());
    }
}
