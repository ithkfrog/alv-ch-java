package ch.alv.components.data.mock.jpa;

import ch.alv.components.data.jpa.BaseJpaModelItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity for Repository test concerns
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "jpa_test_entity")
public class MockJpaEntity extends BaseJpaModelItem {

    @Column
    private String myAttribute;

    public String getMyAttribute() {
        return myAttribute;
    }

    public void setMyAttribute(String myAttribute) {
        this.myAttribute = myAttribute;
    }
}
