package ch.alv.components.data.elastic;

import ch.alv.components.data.mock.elastic.MockElasticEntity;
import ch.alv.components.data.mock.elastic.MockElasticEntitySearchAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.junit.Assert.assertEquals;


/**
 * Unit tests for the {@link ElasticSearchAdapter} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/elastic-search-data-store-search-adapter-test-context.xml")
public class ElasticSearchAdapterTest {

    @Resource
    private ElasticsearchRepository<MockElasticEntity, String> persistenceRepository;

    @Resource
    private MockElasticEntitySearchAdapter adapter;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    private MockElasticEntity entity;

    @Before
    public void before() {
        dropAndCreateTestIndices();
        indexTestData();
    }

    private void dropAndCreateTestIndices() {
        elasticsearchTemplate.deleteIndex(ElasticBaseSearchRepositoryTestEntity.class);
        elasticsearchTemplate.createIndex(ElasticBaseSearchRepositoryTestEntity.class);
        elasticsearchTemplate.putMapping(ElasticBaseSearchRepositoryTestEntity.class);
        elasticsearchTemplate.refresh(ElasticBaseSearchRepositoryTestEntity.class, true);
    }

    private void indexTestData() {
        entity = new MockElasticEntity();
        entity.setMyAttribute("testAttributeValue");
        entity = persistenceRepository.save(entity);
    }

    @Test
    public void testFetchFromSourceSingle() {
        assertEquals(entity.getId(), adapter.fetchFromSource(entity.getId()).getId());
    }

    @Test
    public void testFetchFromSourceMulti() {
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
        List<MockElasticEntity> items = adapter.fetchFromSource(null, query);
        List<MockElasticEntity> otherItems = adapter.fetchFromSource(null, null);
        assertEquals(items.size(), otherItems.size());
    }
}
