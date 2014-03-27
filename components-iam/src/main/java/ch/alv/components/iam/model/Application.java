package ch.alv.components.iam.model;

import ch.alv.components.persistence.model.BaseJpaModelItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Application entity (works with table 'am_iam_application')
 *
 * @since 1.0.0
 */
@Entity
@Table(name = "am_iam_application")
public class Application extends BaseJpaModelItem {

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
