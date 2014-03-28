package ch.alv.components.persistence.repository;

import ch.alv.components.persistence.model.BaseJpaModelItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity for Repository test concerns
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "test_entity")
public class TestEntity extends BaseJpaModelItem {

    @Column
    private String myAttribute;

    public String getMyAttribute() {
        return myAttribute;
    }

    public void setMyAttribute(String myAttribute) {
        this.myAttribute = myAttribute;
    }
}
