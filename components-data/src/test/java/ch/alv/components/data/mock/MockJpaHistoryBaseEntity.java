package ch.alv.components.data.mock;

import ch.alv.components.data.model.BaseDateRangeAwareAuditableItem;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Mock implementation of a base JPA entity thats data should be historised.
 *
 * @since 1.0.0
 */
@MappedSuperclass
public class MockJpaHistoryBaseEntity extends BaseDateRangeAwareAuditableItem {

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
