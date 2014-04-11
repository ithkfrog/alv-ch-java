package ch.alv.components.iam.model;


import ch.alv.components.data.jpa.BaseJpaModelItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Role entity (works with table 'module_iam_role')
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "module_iam_role")
public class Role extends BaseJpaModelItem {

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
