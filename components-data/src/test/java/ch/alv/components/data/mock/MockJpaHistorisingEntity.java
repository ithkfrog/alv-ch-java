package ch.alv.components.data.mock;

import ch.alv.components.data.listener.AuditableEntityListener;
import ch.alv.components.data.model.BaseDateRangeAwareAuditableItem;
import ch.alv.components.data.model.EntityState;
import ch.alv.components.data.model.HistorisingEntity;

import javax.persistence.*;

/**
 * Mock implementation of a JPA historising entity.
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "mock_table_hist")
@EntityListeners(AuditableEntityListener.class)
public class MockJpaHistorisingEntity extends BaseDateRangeAwareAuditableItem implements HistorisingEntity<String> {

    @Column
    private String historisedEntityId;

    @Column
    @Enumerated(EnumType.STRING)
    private EntityState entityState;

    @Override
    public void setHistorisedEntityId(String historisedEntityId) {
        this.historisedEntityId = historisedEntityId;
    }

    @Override
    public String getHistorizedEntityId() {
        return historisedEntityId;
    }

    @Override
    public void setEntityState(EntityState entityState) {
        this.entityState = entityState;
    }

    @Override
    public EntityState getEntityState() {
        return entityState;
    }

}
