package ch.alv.components.data.listener;

import ch.alv.components.data.mock.MockJpaHistorisedEntity;
import ch.alv.components.data.mock.MockJpaHistorisingEntity;
import ch.alv.components.data.model.EntityState;
import ch.alv.components.data.repository.Repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link AuditableEntityListener} class.
 *
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/auditable-entity-listener-test-context.xml")
public class AuditableEntityListenerTest {

    @Resource
    private Repository repo;

    @Resource
    private EntityManagerFactory emf;

    private MockJpaHistorisedEntity entity;
    private String entityId;

    @Before
    public void init() {
        List<MockJpaHistorisingEntity> histories = repo.find(MockJpaHistorisingEntity.class);

        for (MockJpaHistorisingEntity history : histories) {
            repo.delete(history.getId(), MockJpaHistorisingEntity.class);
        }

        entity = new MockJpaHistorisedEntity();
        entity.setValidFrom(new Date());
        entity.setValidTo(new Date());
        entity = (MockJpaHistorisedEntity) repo.save(entity, MockJpaHistorisedEntity.class);
        entityId = entity.getId();
    }

    @Test
    public void testCreateEntry() {
        List<MockJpaHistorisingEntity> historyEntries = repo.find(MockJpaHistorisingEntity.class);
        Collections.sort(historyEntries, new HistorySorter());
        assertEquals(1, historyEntries.size());
        MockJpaHistorisingEntity createdEntry = historyEntries.get(0);
        assertEquals(entity.getId(), createdEntry.getHistorizedEntityId());
        assertEquals(EntityState.INITIAL, createdEntry.getEntityState());
        assertEquals(entity.getVersion(), createdEntry.getVersion());
    }

    @Test
    public void testUpdateEntry() {
        MockJpaHistorisedEntity entity = (MockJpaHistorisedEntity) repo.find(entityId, MockJpaHistorisedEntity.class);
        entity.setName("testName");
        entity = (MockJpaHistorisedEntity) repo.save(entity, MockJpaHistorisedEntity.class);
        List<MockJpaHistorisingEntity> historyEntries = repo.find(MockJpaHistorisingEntity.class);
        Collections.sort(historyEntries, new HistorySorter());
        assertEquals(2, historyEntries.size());
        MockJpaHistorisingEntity updatedEntry = historyEntries.get(0);
        assertEquals(entity.getId(), updatedEntry.getHistorizedEntityId());
        assertEquals(EntityState.UPDATED, updatedEntry.getEntityState());
        assertEquals(entity.getVersion(), updatedEntry.getVersion());
    }

    @Test
    public void testDeleteEntry() {
        repo.delete(entity.getId(), MockJpaHistorisedEntity.class);
        List<MockJpaHistorisingEntity> historyEntries = repo.find(MockJpaHistorisingEntity.class);
        Collections.sort(historyEntries, new HistorySorter());
        assertEquals(2, historyEntries.size());
        MockJpaHistorisingEntity deleteEntry = historyEntries.get(0);
        assertEquals(entity.getId(), deleteEntry.getHistorizedEntityId());
        assertEquals(EntityState.DELETED, deleteEntry.getEntityState());
        assertEquals(entity.getVersion(), deleteEntry.getVersion());
    }

    public class HistorySorter implements Comparator<MockJpaHistorisingEntity> {

        @Override
        public int compare(MockJpaHistorisingEntity o1, MockJpaHistorisingEntity o2) {
            return o1.getLastUpdateOn().compareTo(o2.getLastUpdateOn()) * -1;
        }
    }
}
