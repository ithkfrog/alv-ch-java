package ch.alv.components.data.model;

import ch.alv.components.core.beans.ModelItem;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Base implementation of an entity.
 *
 * @since 1.0.0
 */
@MappedSuperclass
public class BaseModelItem implements ModelItem {

    @org.springframework.data.annotation.Id
    @Id
    @GenericGenerator(name = "uuid_generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid_generator")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Version
    @Column(name = "version")
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
