package ch.alv.components.data.adapter;

import ch.alv.components.core.search.MapBasedValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.mock.MockElasticsearchQueryProvider;
import ch.alv.components.data.mock.MockTestDocument;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link ch.alv.components.data.query.DefaultQueryFactory} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/elasticsearch-data-store-adapter-test-context.xml")
public class ElasticDataStoreAdapterTest {

    @Resource
    private DataStoreAdapter<MockTestDocument, String> adapter;

    @Resource
    private ElasticsearchTemplate template;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private String id = UUID.randomUUID().toString();

    @Test
    public void testFind() throws DataLayerException {
        template.deleteIndex(MockTestDocument.class);
        MockTestDocument doc = new MockTestDocument();
        doc.setId(id);
        assertEquals(id, adapter.save(doc, MockTestDocument.class).getId());
        assertNotNull(adapter.find(id, MockTestDocument.class));
        assertNull(adapter.find("unknownItem", MockTestDocument.class));

        assertEquals(1, adapter.find(MockTestDocument.class).size());
        assertEquals(1, adapter.find(MockElasticsearchQueryProvider.NAME, new MapBasedValuesProvider(), MockTestDocument.class).size());

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage("Could not execute query with name 'unknownQuery'.");
        adapter.find("unknownQuery", new MapBasedValuesProvider(), MockTestDocument.class);
    }

    @Test
    public void testSave() throws DataLayerException {
        template.deleteIndex(MockTestDocument.class);
        assertFalse(template.indexExists(MockTestDocument.class));
        adapter.save(new MockTestDocument(), MockTestDocument.class);
        assertTrue(template.indexExists(MockTestDocument.class));

        MockTestDocument doc = new MockTestDocument();
        doc.setId(id);
        MockTestDocument newDoc = adapter.save(doc, MockTestDocument.class);
        assertEquals(doc.getId(), newDoc.getId());
    }

    @Test
    public void testDelete() throws DataLayerException {
        template.deleteIndex(MockTestDocument.class);
        MockTestDocument doc = new MockTestDocument();
        doc.setId(id);
        adapter.save(doc, MockTestDocument.class);
        adapter.delete(id, MockTestDocument.class);
        assertNull(adapter.find(id, MockTestDocument.class));
    }


}
