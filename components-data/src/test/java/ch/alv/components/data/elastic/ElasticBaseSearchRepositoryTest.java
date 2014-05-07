package ch.alv.components.data.elastic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;


/**
 * Unit tests for the Repositories
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/elastic-base-search-repository-test-context.xml")
public class ElasticBaseSearchRepositoryTest {

    @Resource
    private ElasticsearchRepository<ElasticBaseSearchRepositoryTestEntity, String> persistenceRepository;

    @Resource
    private ElasticBaseSearchRepository searchRepository;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    private ElasticBaseSearchRepositoryTestEntity entity;

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
        entity = new ElasticBaseSearchRepositoryTestEntity();
        entity.setMyAttribute("testAttributeValue");
        entity = persistenceRepository.save(entity);
    }

    @Test
    public void testGetAll() {
        assertEquals(1, searchRepository.getAll().getNumberOfElements());
    }

    @Test
    public void testGetAllWithPageable() {
        assertEquals(1, searchRepository.getAll(new PageRequest(0, 100)).getNumberOfElements());
        searchRepository.find(null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetOne() {
        assertEquals(entity.getId(), ((ElasticBaseSearchRepositoryTestEntity) searchRepository.getOne(entity.getId())).getId());
    }
}
