package ch.alv.components.data.jpa;

import ch.alv.components.data.mock.jpa.MockJpaEntity;
import ch.alv.components.data.mock.jpa.MockJpaEntitySearchAdapter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the {@link JpaSearchAdapter} class
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/jpa-base-search-repository-test-context.xml")
public class JpaSearchAdapterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Resource
    private CrudRepository<MockJpaEntity, String> repository;

    @Resource
    private MockJpaEntitySearchAdapter adapter;

    private MockJpaEntity entity;

    @Before
    public void before() {
        entity = new MockJpaEntity();
        entity.setMyAttribute("testAttributeValue");
        entity = repository.save(entity);
    }

    @Test
    public void testFetchFromSourceSingle() {
        assertEquals(entity.getId(), adapter.fetchFromSource(entity.getId()).getId());
    }

    @Test
    public void testFetchFromSourceMulti() {
        List<MockJpaEntity> items = adapter.fetchFromSource(null, "select m from MockJpaEntity m");
        List<MockJpaEntity> otherItems = adapter.fetchFromSource(null, null);
        assertEquals(items.size(), otherItems.size());
    }

    @Test
    public void testFetchFromSourceMultiNonString() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Parameter 'search' must be a jpa query string.");
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
        adapter.fetchFromSource(null, query);
    }

}
