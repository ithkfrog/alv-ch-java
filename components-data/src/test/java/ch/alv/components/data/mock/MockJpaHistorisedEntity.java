package ch.alv.components.data.mock;

import ch.alv.components.data.listener.AuditableEntityListener;
import ch.alv.components.data.model.Historised;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * Mock implementation of a JPA historised entity.
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "mock_table")
@EntityListeners(AuditableEntityListener.class)
@Historised(targetClass = MockJpaHistorisingEntity.class)
public class MockJpaHistorisedEntity extends MockJpaHistoryBaseEntity {

}
