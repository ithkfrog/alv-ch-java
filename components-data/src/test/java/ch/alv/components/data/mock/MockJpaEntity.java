package ch.alv.components.data.mock;

import ch.alv.components.data.model.BaseModelItem;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Mock document for jpa tests
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "mock_jpa_entity")
public class MockJpaEntity extends BaseModelItem {

}
