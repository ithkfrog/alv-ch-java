package ch.alv.components.data.adapter;

import ch.alv.components.core.search.MapBasedValuesProvider;
import ch.alv.components.data.DataLayerException;
import ch.alv.components.data.mock.MockJpaEntity;
import ch.alv.components.data.mock.MockJpaQueryProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link ch.alv.components.data.query.DefaultQueryFactory} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/jpa-data-store-adapter-test-context.xml")
@SuppressWarnings("unchecked")
public class JpaDataStoreAdapterTest {

    @Resource
    private DataStoreAdapter adapter;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFind() throws DataLayerException {
        MockJpaEntity entity = new MockJpaEntity();
        entity = (MockJpaEntity) adapter.save(entity, MockJpaEntity.class);
        assertNotNull(adapter.find(entity.getId(), MockJpaEntity.class));
        assertNull(adapter.find("unknownItem", MockJpaEntity.class));

        assertEquals(1, adapter.find(MockJpaEntity.class).size());
        assertEquals(1, adapter.find(MockJpaQueryProvider.NAME, new MapBasedValuesProvider(), MockJpaEntity.class).size());

        expectedException.expect(DataLayerException.class);
        expectedException.expectMessage("Could not execute query with name 'unknownQuery'.");
        adapter.find("unknownQuery", new MapBasedValuesProvider(), MockJpaEntity.class);
    }

    @Test
    public void testCreate() throws DataLayerException {
        assertNotNull(adapter.save(new MockJpaEntity(), MockJpaEntity.class).getId());
    }

    @Test
    public void testUpdate() throws DataLayerException {
        MockJpaEntity entity = new MockJpaEntity();

        entity = (MockJpaEntity) adapter.save(entity, MockJpaEntity.class);
        String id = entity.getId();
        assertNull(entity.getName());
        entity.setName("testName");
        adapter.save(entity, MockJpaEntity.class);
        entity = (MockJpaEntity) adapter.find(id, MockJpaEntity.class);
        assertEquals("testName", entity.getName());
    }

    @Test
    public void testDelete() throws DataLayerException {
        MockJpaEntity doc = new MockJpaEntity();
        doc = (MockJpaEntity) adapter.save(doc, MockJpaEntity.class);
        adapter.delete(doc.getId(), MockJpaEntity.class);
        assertNull(adapter.find(doc.getId(), MockJpaEntity.class));
        adapter.delete("unknown", MockJpaEntity.class);
    }

}
